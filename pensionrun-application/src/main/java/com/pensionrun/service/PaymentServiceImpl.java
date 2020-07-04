package com.pensionrun.service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pensionrun.dao.AccountDao;
import com.pensionrun.dao.OrderDao;
import com.pensionrun.dao.PaymentCancelCardDao;
import com.pensionrun.dao.PaymentCancelVbankDao;
import com.pensionrun.dao.PaymentCardDao;
import com.pensionrun.dao.PaymentDao;
import com.pensionrun.dao.PaymentVbankDao;
import com.pensionrun.dao.PensionDao;
import com.pensionrun.dao.RoomDao;
import com.pensionrun.dao.RoomPriceDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NicePayDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.dto.PaymentCancelVbankDto;
import com.pensionrun.entity.Account;
import com.pensionrun.entity.Order;
import com.pensionrun.entity.Payment;
import com.pensionrun.entity.PaymentCancelCard;
import com.pensionrun.entity.PaymentCancelVbank;
import com.pensionrun.entity.PaymentCard;
import com.pensionrun.entity.PaymentVbank;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.Room;
import com.pensionrun.entity.RoomPrice;
import com.pensionrun.entity.RoomPriceId;
import com.pensionrun.util.BiztokUtil;
import com.pensionrun.util.DateUtil;
import com.pensionrun.util.NicePayUtil;
import com.pensionrun.util.SlackUtil;
import com.pensionrun.util.WooriPensionProvider;
import com.pensionrun.util.WooriPensionUtil;
import com.pensionrun.vo.BiztokVo;
import com.pensionrun.vo.SlackVo;
import com.pensionrun.vo.WooriPensionVo;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private PaymentCardDao paymentCardDao;
	@Autowired
	private PaymentVbankDao paymentVbankDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private PensionDao pensionDao;
	@Autowired
	private PaymentCancelCardDao paymentCancelCardDao;
	@Autowired
	private PaymentCancelVbankDao paymentCancelVbankDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoomPriceDao roomPriceDao;

	private int expDate = 60;


	@Override
	public boolean nicePayStart(Model model, OrderDto orderDto, String paymentType) {
		// TODO Auto-generated method stub
		// order create
		Order order = new Order();
		Room room = roomDao.readById(orderDto.getIdfRoom());
		BeanUtils.copyProperties(orderDto, order);
		order.setRoom(room);
		String reservationCode = null;
		try {
			Account account = accountDao.readById(8);
			// Account
			// account=accountDao.readById(SecurityContextHolder.getContext().getAuthentication().getName());
			order.setAccount(account);
			orderDao.create(order);
			reservationCode = wooriPensionReservationHolding(order, paymentType, 0, 0);
		} catch (Exception e) {
			return false;
		}
		if (reservationCode != null&&!reservationCode.equals("")) {
			// 가예약 성공
			order.setReservationCode(reservationCode);
			// 우리펜션 코드 저장
			orderDao.update(order);
		} else {
			// 가예약 실패
			updateOrderState(order, '2');
			SlackUtil.sendNoticeSlackMessage(setSlcakVo(order, "우리펜션 가예약 실패","", "danger"));	
			// 이미 예약된 펜션 or 다시 시도
			return false;
		}

		String ediDate = NicePayUtil.getyyyyMMddHHmmss();
		String hash_String = null;
		if (paymentType.equals(0)||paymentType.equals(2)) {
			hash_String = NicePayUtil.getHashString(ediDate, NicePayUtil.MERCHANT_ID_1,
					(int) orderDto.getAmount() + "", NicePayUtil.MERCHANT_KEY_1);
			model.addAttribute("MID", NicePayUtil.MERCHANT_ID_1);
		} else if(paymentType.equals(1)){
			hash_String = NicePayUtil.getHashString(ediDate, NicePayUtil.MERCHANT_ID_2,
					(int) orderDto.getAmount() + "", NicePayUtil.MERCHANT_KEY_2);
			model.addAttribute("MID", NicePayUtil.MERCHANT_ID_2);
		}
		Pension pension = pensionDao.readById(room.getPension().getIdfPension());
		model.addAttribute("GoodsCnt", "1");
		model.addAttribute("Moid", orderDto.getIdfOrder());
		model.addAttribute("BuyerTel", orderDto.getMobile());
		model.addAttribute("BuyerEmail", orderDto.getEmail());
		model.addAttribute("PayMethod", NicePayUtil.convertPaymentType(paymentType));
		try {
			model.addAttribute("VbankExpDate", NicePayUtil.getVbankExpDate(expDate));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("ReturnURL", "http://dev.pensionday.kr:8080/test/nicePayCallback");
		model.addAttribute("RetryURL", "http://dev.pensionday.kr:8080/test/nicePayDbCallback");
		model.addAttribute("CharSet", "utf-8");
		model.addAttribute("Moid", order.getIdfOrder());
		model.addAttribute("Amt", (int) orderDto.getAmount());
		model.addAttribute("GoodsName",
				pension.getName() + " " + room.getName() + " " + orderDto.getPeopleCount() + "인");
		model.addAttribute("hashString", hash_String);
		model.addAttribute("ediDate", ediDate);
		model.addAttribute("BuyerName", orderDto.getName());
		return true;
	}
	@Override
	public JsonDto<Object> nicePayCallback(NicePayDto nicePayDto) {
		Order order = orderDao.readById(Integer.parseInt(nicePayDto.getMoid()));
		JsonDto<Object> jDto = new JsonDto<Object>();
		if (!nicePayDto.getAmt().equals(((int) order.getAmount()) + "")) {
			// 결제 가격 맞지 않음
			// 나이스페이 결제 취소
			NicePayUtil.cancelNicePay(nicePayDto.getTID(), Float.parseFloat(nicePayDto.getAmt()));
			updateOrderState(order, '3');
			jDto.setResultCode("F");
			jDto.setResultMessage(nicePayDto.getResultMsg());
			return jDto;
		}
		if (nicePayDto.getResultCode().equals("3001") || nicePayDto.getResultCode().equals("4100")) {
			// 결제 성공
			if (nicePayDto.getPayMethod().equals("CARD")) {
				// 진예약성공
				if (wooriPensionReservationConfirm(order)) {
				} else {
					// 진예약실패
					SlackUtil.sendNoticeSlackMessage(setSlcakVo(order, "우리펜션 진예약 실패","", "danger"));
					updateOrderState(order, '2');
					jDto.setResultCode("F");
					jDto.setResultMessage(nicePayDto.getResultMsg());
					return jDto;
				}
			}
			Payment payment = new Payment();
			payment.setIdfPayment(nicePayDto.getTID());
			payment.setOrder(order);
			payment.setType(NicePayUtil.convertPaymentType(nicePayDto.getPayMethod()));
			payment.setAmount(Float.parseFloat(nicePayDto.getAmt()));
			payment.setResultCode(nicePayDto.getResultCode());
			payment.setResultMessage(nicePayDto.getResultMsg());

			paymentDao.create(payment);
			if (nicePayDto.getPayMethod().equals("CARD")) {
				updateOrderState(order, '1');
				PaymentCard paymentCard = new PaymentCard();
				paymentCard.setPayment(payment);
				paymentCard.setNumber(nicePayDto.getCardNo());
				paymentCard.setCode(nicePayDto.getCardCode());
				paymentCard.setName(nicePayDto.getCardName());
				paymentCard.setMonth(nicePayDto.getCardQuota());
				paymentCard.setAuthCode(nicePayDto.getAuthCode());
				paymentCard.setAuthDate(nicePayDto.getAuthDate());
				paymentCardDao.create(paymentCard);
				sendBiztok(order);
				jDto.setResultCode("S");
				jDto.setDataObject(paymentCard);

			} else if (nicePayDto.getPayMethod().equals("VBANK")) {
				PaymentVbank paymentVbank = new PaymentVbank();
				paymentVbank.setPayment(payment);
				paymentVbank.setExpiryDate(nicePayDto.getVbankExpDate());
				paymentVbank.setName(nicePayDto.getVbankBankName());
				paymentVbank.setNumber(nicePayDto.getVbankNum());
				paymentVbank.setCode(nicePayDto.getFnCd());
				paymentVbankDao.create(paymentVbank);
				order.setState('e');
				sendBiztok(order,paymentVbank);
				jDto.setResultCode("S");
				jDto.setDataObject(paymentVbank);
			}


		} else {
			// 결제 실패
			order.setState('3');
			orderDao.update(order);
			jDto.setResultCode("F");
			jDto.setResultMessage(nicePayDto.getResultMsg());
		}
		// TODO Auto-generated method stub
		return jDto;
	}

	@Override
	// 가상계좌 입금 처리
	public void nicePayVbankCallback(NicePayDto nicePayDto) {
		// TODO Auto-generated method stub

		int idfOrder = Integer.parseInt(nicePayDto.getMoid());
		Order order = orderDao.readById(idfOrder);
		updateOrderState(order, '1');
		PaymentVbank paymentVbank = paymentVbankDao.readById(nicePayDto.getTID());
		paymentVbank.setDepositDate(nicePayDto.getAuthDate());
		paymentVbank.setDepositName(nicePayDto.getVbankInputName());
		paymentVbank.setReceiptNumber(nicePayDto.getRcptTID());
		if(nicePayDto.getRcptType()!=null){
			paymentVbank.setReceiptType(nicePayDto.getRcptType().charAt(0));
		}
		if(nicePayDto.getRcptAuthCode()!=null){
			paymentVbank.setReceiptAuthCode(nicePayDto.getRcptAuthCode());
		}
		if(nicePayDto.getRcptTID()!=null){
			paymentVbank.setReceiptNumber(nicePayDto.getRcptTID());
		}
		paymentVbankDao.update(paymentVbank);
		wooriPensionReservationConfirm(order);
		sendBiztok(order);

	}

	@Override
	public JsonDto<Object> cancelPaymentCard(int idfOrder) {
		// TODO Auto-generated method stub
		JsonDto<Object> jDto = new JsonDto<Object>();
		Order order = orderDao.readById(idfOrder);
		Payment payment = paymentDao.readByIdfOrder(idfOrder);

		int diffDay = 10;
		try {
			diffDay = DateUtil.calculateDiffDay(DateUtil.dateToString(new Date(), "yyyy-MM-dd"), order.getCheckinDate(),
					"yyyy-MM-dd");
		} catch (Exception e) {
			jDto.setResultCode("F");
			jDto.setResultMessage("에러가 발생했습니다.");
			return jDto;
		}

		String now = DateUtil.dateToString(new Date(), "yyyyMMddHHmm");

		PaymentCancelCard paymentCancelCard = new PaymentCancelCard();
		paymentCancelCard.setPayment(payment);
		paymentCancelCard.setReceiveDate(now);
		System.out.println(diffDay);
		if (diffDay > 7) {// 체크인 날짜가 8일전 이상인 카드 결제 경우 자동 취소
			updateOrderState(order, '5');
			//NicePayUtil.cancelNicePay(payment.getIdfPayment(), payment.getAmount());
			paymentCancelCard.setRefundAmount(payment.getAmount());
			paymentCancelCard.setProceedDate(now);
			paymentDao.update(payment);
			sendBiztok(order);
		} else if (diffDay < 1) {// 체크인 날짜가 당일이거나 지난 경우
			jDto.setResultCode("F");
			jDto.setResultMessage("취소가 불가능합니다.");
			return jDto;
		} else {
			updateOrderState(order, '4');
			SlackUtil.sendNoticeSlackMessage(setSlcakVo(order, "취소 신청","", "good"));
			sendBiztok(order);
		}
		paymentCancelCardDao.create(paymentCancelCard);
		try {
			reservationCancellation(order);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jDto.setResultCode("S");
		return jDto;
	}

	@Override
	public JsonDto<Object> cancelPaymentVbank(int idfOrder, PaymentCancelVbankDto paymentCancelVbankDto) {
		// TODO Auto-generated method stub
		JsonDto<Object> jDto = new JsonDto<Object>();
		Order order = orderDao.readById(idfOrder);
		int diffDay = 10;
		try {
			diffDay = DateUtil.calculateDiffDay(DateUtil.dateToString(new Date(), "yyyy-MM-dd"), order.getCheckinDate(),
					"yyyy-MM-dd");
		} catch (Exception e) {
			jDto.setResultCode("F");
			jDto.setResultMessage("에러가 발생했습니다.");
			return jDto;
		}
		if (diffDay < 1) {// 체크인 날짜가 당일이거나 지난 경우
			jDto.setResultCode("F");
			jDto.setResultMessage("취소가 불가능합니다.");
			return jDto;
		} else {
			updateOrderState(order, '4');
			sendBiztok(order);
		}

		try {
			reservationCancellation(order);
		} catch (ParseException e) {
			// TODO Auto-generated catch 
			e.printStackTrace();
		}

		Payment payment = paymentDao.readByIdfOrder(idfOrder);
		PaymentCancelVbank paymentCancelVbank = new PaymentCancelVbank();
		paymentCancelVbank.setPayment(payment);
		paymentCancelVbank.setDepositName(paymentCancelVbankDto.getDepositName());
		paymentCancelVbank.setDepositNumber(paymentCancelVbankDto.getDepositNumber());
		paymentCancelVbank.setDepositBankName(paymentCancelVbankDto.getDepositBankName());
		paymentCancelVbankDao.create(paymentCancelVbank);
		SlackUtil.sendNoticeSlackMessage(setSlcakVo(order,"취소 신청","", "good"));
		jDto.setResultCode("S");
		return jDto;
	}

	@Override
	public JsonDto<Object> cancelOrder(int idfOrder) {
		// TODO Auto-generated method stub
		Order order = orderDao.readById(idfOrder);
		updateOrderState(order, '6');
		JsonDto<Object> jDto = new JsonDto<Object>();
		jDto.setResultCode("S");
		jDto.setDataObject(order);
		return jDto;
	}

	private void updateOrderState(Order order, char state) {
		order.setState(state);
		orderDao.update(order);
	}
	private void sendBiztok(Order order){//예약 정보를 카카오톡VO로 매
		BiztokVo biztokVo=new BiztokVo();
		biztokVo.setUserName(order.getName());
		biztokVo.setPhone(order.getMobile().replaceAll("-", ""));
		biztokVo.setBookingNumber("p"+DateUtil.dateToString(order.getRegDate(), "yyyyMMddHHmmss")+order.getIdfOrder());
		biztokVo.setInwon(order.getPeopleCount()+"");
		DecimalFormat df = new DecimalFormat("#,##0");
		biztokVo.setPrice(df.format((int)order.getAmount()));
		try {
			biztokVo.setTotalDay(order.getCheckinDate()+"("+DateUtil.getDateDay(order.getCheckinDate(), "yyyy-MM-dd")+") "+DateUtil.calculateDiffDay(order.getCheckinDate(), order.getCheckoutDate(), "yyyy-MM-dd")+"박");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Room room=roomDao.readById(order.getRoom().getIdfRoom());
		biztokVo.setPensionName(pensionDao.readById(room.getPension().getIdfPension()).getName());
		biztokVo.setRoomName(room.getName());
		biztokVo.setSendType(BiztokUtil.changeSendType(order.getState()));
		BiztokUtil.sendBiztok(biztokVo);
	}
	private void sendBiztok(Order order,PaymentVbank paymentVbank){//예약 정보를 카카오톡VO로 매
		BiztokVo biztokVo=new BiztokVo();
		biztokVo.setUserName(order.getName());
		biztokVo.setPhone(order.getMobile().replaceAll("-", ""));
		biztokVo.setBookingNumber("p"+DateUtil.dateToString(order.getRegDate(), "yyyyMMddHHmmss")+order.getIdfOrder());
		biztokVo.setInwon(order.getPeopleCount()+"");
		DecimalFormat df = new DecimalFormat("#,##0");
		biztokVo.setPrice(df.format((int)order.getAmount()));
		try {
			biztokVo.setTotalDay(order.getCheckinDate()+"("+DateUtil.getDateDay(order.getCheckinDate(), "yyyy-MM-dd")+") "+DateUtil.calculateDiffDay(order.getCheckinDate(), order.getCheckoutDate(), "yyyy-MM-dd")+"박");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Room room=roomDao.readById(order.getRoom().getIdfRoom());
		biztokVo.setPensionName(pensionDao.readById(room.getPension().getIdfPension()).getName());
		biztokVo.setRoomName(room.getName());
		biztokVo.setSendType(BiztokUtil.changeSendType(order.getState()));
		biztokVo.setAccountNumber(paymentVbank.getName() + " "+ paymentVbank.getNumber() + " (주)펜션으로튀어라");
		biztokVo.setAccountNumberTime(DateUtil.dateFormatChange(paymentVbank.getExpiryDate(),"yyMMddHHmmss","yyyy년MM월dd일 HH시 mm분 ss초 까지"));
		BiztokUtil.sendBiztok(biztokVo);
	}

	private SlackVo setSlcakVo(Order order,String notice,String message,String color){
		System.out.println(order.toString());
		SlackVo slackVo=new SlackVo();
		BeanUtils.copyProperties(order, slackVo);
		if(order.getRegDate()==null)
			slackVo.setRegDate(new Date());
		slackVo.setNoitce(notice);
		slackVo.setMessage(message);
		slackVo.setColor(color);
		return slackVo;
	}
	// 우리펜션 가예약
	private String wooriPensionReservationHolding(Order order, String type, float discount, float extra)
			throws UnsupportedEncodingException {

		WooriPensionVo wooriPensionVo = new WooriPensionVo();
		int idfRoom = order.getRoom().getIdfRoom();
		String idfPensionProvider = WooriPensionProvider.pensionProvierByIdfRoom.get(idfRoom);
		BeanUtils.copyProperties(order, wooriPensionVo);
		wooriPensionVo.setIdfProvider(idfPensionProvider);
		wooriPensionVo.setIdfProvierRoom(
				WooriPensionProvider.roomProvider.get(idfPensionProvider).getKey(idfRoom).toString());
		wooriPensionVo.setDiscount(discount);
		wooriPensionVo.setExtraAmount(extra);
		if (type.equals("0")) {
			wooriPensionVo.setType("C");
		} else {
			wooriPensionVo.setType("B");
		}
		return WooriPensionUtil.reservationHolding(wooriPensionVo);
	}

	// 우리펜션 진예약
	private boolean wooriPensionReservationConfirm(Order order) {
		int idfRoom = order.getRoom().getIdfRoom();
		String idfPensionProvider = WooriPensionProvider.pensionProvierByIdfRoom.get(idfRoom);
		WooriPensionVo wooriPensionVo = new WooriPensionVo();
		wooriPensionVo.setIdfOrder(order.getIdfOrder());
		wooriPensionVo.setReservationCode(order.getReservationCode());
		wooriPensionVo.setIdfProvider(idfPensionProvider);
		wooriPensionVo.setIdfProvierRoom(
				WooriPensionProvider.roomProvider.get(idfPensionProvider).getKey(idfRoom).toString());
		boolean result = WooriPensionUtil.reservationConfirm(wooriPensionVo).equals("1");
		if (!result) {
			SlackUtil.sendNoticeSlackMessage(setSlcakVo(order,"우리펜션 진예약 실패","", "danger"));
		}
		return result;
	}

	// 우리펜션 취소
	public void reservationCancellation(Order order) throws ParseException {
		int idfOrder = order.getIdfOrder();
		WooriPensionVo wooriPensionVo = new WooriPensionVo();
		BeanUtils.copyProperties(order, wooriPensionVo);
		wooriPensionVo.setReservationCode(order.getReservationCode());
		wooriPensionVo.setRefund(
				WooriPensionUtil.calcurateCancelCharege(wooriPensionVo,DateUtil.dateToString(new Date(), "yyyy-MM-ss")));
		wooriPensionVo.setIdfOrder(idfOrder);
		wooriPensionVo.setAmount(order.getAmount());
		if (!WooriPensionUtil.reservationCancellation(wooriPensionVo).equals("1")){
			SlackUtil.sendNoticeSlackMessage(setSlcakVo(order, "우리펜션 취소 실패","", "danger"));
		}
	}
	public void blockRoom(Order order) throws Exception{
		List<String> dayList=DateUtil.getDiffDayList(order.getCheckinDate(), order.getCheckoutDate(),  "yyyy-MM-ss");
		for(int i=0;i<dayList.size()-1;i++){
			RoomPriceId roomPriceId = new RoomPriceId();				
			roomPriceId.setIdfRoom(order.getRoom().getIdfRoom());
			roomPriceId.setCheckDate(dayList.get(i));
			// 가격이존재하지 않으면 인서트
			RoomPrice roomPrice = roomPriceDao.readById(roomPriceId);
			roomPrice.setRemainRoomCount((byte)0);					
			roomPriceDao.update(roomPrice);
		}

	}
}
