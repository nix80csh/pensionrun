package com.pensionrun.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Order;
import com.pensionrun.entity.Pension;
import com.pensionrun.vo.OrderSearchVo;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao{
	@PersistenceContext
	private EntityManager em;
	public List<Order> readByOrderSearchVo(OrderSearchVo orderSearchVo) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> q = cb.createQuery(Order.class);
		Root<Order> root = q.from(Order.class);
		List<Predicate> predicates=new ArrayList<Predicate>();

		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
			predicates.add(cb.equal(root.get("name"), orderSearchVo.getName()));
		}
		
		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
			predicates.add(cb.equal(root.get("mobile"), orderSearchVo.getMobile()));
		}
		
		if(orderSearchVo.getState()!=0){
			predicates.add(cb.equal(root.get("state"), orderSearchVo.getState()));
		}
		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
			Expression<String> key = root.get("checkinDate");
			predicates.add(cb.greaterThanOrEqualTo(key, orderSearchVo.getCheckinDate()));
		}
		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
			Expression<String> key = root.get("checkoutDate");
			predicates.add(cb.lessThanOrEqualTo(key, orderSearchVo.getCheckoutDate()));
		}
		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
			Expression<Date> key = root.get("regDate");
			SimpleDateFormat original_format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				predicates.add(cb.between(key, original_format.parse(orderSearchVo.getStartRegDate()),original_format.parse(orderSearchVo.getEndRegDate())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			}
		}
		return (List<Order>)em.createQuery(q.where(cb.and(predicates.toArray(new  Predicate[]{}))))
				.setFirstResult(orderSearchVo.getUnit()*(orderSearchVo.getPage()-1))
				.setMaxResults(orderSearchVo.getUnit()).getResultList();
	}
	@Override
	public int getMaxSizeByOrderSearchVo(OrderSearchVo orderSearchVo) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Order> root = q.from(Order.class);
		List<Predicate> predicates=new ArrayList<Predicate>();

		q.select((cb.countDistinct(root)));
		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
			predicates.add(cb.equal(root.get("name"), orderSearchVo.getName()));
		}
		
		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
			predicates.add(cb.equal(root.get("mobile"), orderSearchVo.getMobile()));
		}
		
		if(orderSearchVo.getState()!=0){
			predicates.add(cb.equal(root.get("state"), orderSearchVo.getState()));
		}
		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
			Expression<String> key = root.get("checkinDate");
			predicates.add(cb.greaterThanOrEqualTo(key, orderSearchVo.getCheckinDate()));
		}
		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
			Expression<String> key = root.get("checkoutDate");
			predicates.add(cb.lessThanOrEqualTo(key, orderSearchVo.getCheckoutDate()));
		}
		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
			Expression<Date> key = root.get("regDate");
			SimpleDateFormat original_format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				predicates.add(cb.between(key, original_format.parse(orderSearchVo.getStartRegDate()),original_format.parse(orderSearchVo.getEndRegDate())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			}
		}
		return Integer.parseInt((em.createQuery(q.where(cb.and(predicates.toArray(new  Predicate[]{})))).getSingleResult()).toString());
	}
	@Override
	public List<Order> readByAccount(Integer idfAccount) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from Order o where o.idfAccount = :idfAccount", Order.class);
		q.setParameter("idfAccount",idfAccount);
		return q.getResultList();
	}

	//	@Override
	//	public List<Order> readByOrderSearchVo(OrderSearchVo orderSearchVo) {
	//		boolean and=false;
	//		boolean where=true;
	//		// TODO Auto-generated method stub
	//		String sql = "from Order o ";
	//		Query q = null;
	//		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
	//			Predicate predicate=new Predicate();
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.name like :name";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.mobile like :mobile";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getState()!=0){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.state = :state";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.checkinDate >= :checkinDate";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.checkoutDate <= :checkoutDate";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.regDate between :startRegDate and :endRegDate";
	//			and=true;
	//			where=false;
	//		}
	//
	//		sql+=" order by o.idfOrder desc";
	//		q = em.createQuery(sql, Order.class)
	//				.setFirstResult(orderSearchVo.getUnit()*(orderSearchVo.getPage()-1))
	//				.setMaxResults(orderSearchVo.getUnit());
	//
	//		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
	//			q.setParameter("name","%"+orderSearchVo.getName()+"%");
	//		}
	//		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
	//			q.setParameter("mobile","%"+orderSearchVo.getMobile()+"%");
	//		}
	//		if(orderSearchVo.getState()!=0){
	//			q.setParameter("state",orderSearchVo.getState());
	//		}
	//		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
	//			q.setParameter("checkinDate",orderSearchVo.getCheckinDate());
	//		}
	//		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
	//			q.setParameter("checkoutDate",orderSearchVo.getCheckoutDate());
	//		}
	//		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
	//			q.setParameter("startRegDate",orderSearchVo.getStartRegDate());
	//			q.setParameter("endRegDate",orderSearchVo.getEndRegDate());
	//		}
	//		return q.getResultList();
	//	}
	//
	//
	//	@Override
	//	public int getMaxSizeByOrderSearchVo(OrderSearchVo orderSearchVo) {
	//		// TODO Auto-generated method stub
	//		boolean and=false;
	//		boolean where=true;
	//		// TODO Auto-generated method stub
	//		String sql = "from Order o ";
	//
	//
	//		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.name like :name";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.mobile like :mobile";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getState()!=0){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.state = :state";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.checkinDate >= :checkinDate";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.checkoutDate <= :checkoutDate";
	//			and=true;
	//			where=false;
	//		}
	//		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
	//			sql+=(where?"where ":"")+(and?"and ":"")+"o.regDate between :startRegDate and :endRegDate";
	//			and=true;
	//			where=false;
	//		}
	//		System.out.println(sql);
	//		Query q = em.createQuery(sql, Order.class);
	//
	//		if(orderSearchVo.getName()!=null&&!orderSearchVo.getName().equals("")){
	//			q.setParameter("name","%"+orderSearchVo.getName()+"%");
	//		}
	//		if(orderSearchVo.getMobile()!=null&&!orderSearchVo.getMobile().equals("")){
	//			q.setParameter("mobile","%"+orderSearchVo.getMobile()+"%");
	//		}
	//		if(orderSearchVo.getState()!=0){
	//			q.setParameter("state",orderSearchVo.getState());
	//		}
	//		if(orderSearchVo.getCheckinDate()!=null&&!orderSearchVo.getCheckinDate().equals("")){
	//			q.setParameter("checkinDate",orderSearchVo.getCheckinDate());
	//		}
	//		if(orderSearchVo.getCheckoutDate()!=null&&!orderSearchVo.getCheckoutDate().equals("")){
	//			q.setParameter("checkoutDate",orderSearchVo.getCheckoutDate());
	//		}
	//		if(orderSearchVo.getStartRegDate()!=null&&!orderSearchVo.getStartRegDate().equals("")&&orderSearchVo.getEndRegDate()!=null&&!orderSearchVo.getEndRegDate().equals("")){
	//			q.setParameter("startRegDate",orderSearchVo.getStartRegDate());
	//			q.setParameter("endRegDate",orderSearchVo.getEndRegDate());
	//		}
	//		return q.getResultList().size();
	//	}

}
