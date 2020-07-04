package com.pensionrun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
//@Component
public class WooriPensionProvider {

	public static BidiMap pensionProvier;
	public static  Map<String,BidiMap> roomProvider;
	public static  Map<Integer,String> pensionProvierByIdfRoom;
	static{
		try {
			pensionProvier=new DualHashBidiMap();
			roomProvider=new HashMap<String, BidiMap>();
			pensionProvierByIdfRoom=new HashMap<Integer, String>();
			Connection con = DriverManager.getConnection("jdbc:mysql://pensionrun-db.cxbvtxfz0tu1.ap-northeast-2.rds.amazonaws.com:3306/PensionRun",
					"pensionrun", "vpsxnl2015new!!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idf_pension,idf_provider,idf_provider_room,idf_room FROM PensionRun.room  where idf_provider like 'w%' order by idf_provider");
			int idfPension=0;
			String idfProvider=null;
			BidiMap roomProviderMap=new DualHashBidiMap();
			while(rs.next()){
				int resultIdfPension=rs.getInt("idf_pension");
				if(idfPension!=resultIdfPension){
					idfPension=resultIdfPension;					
					idfProvider=rs.getString("idf_provider");					
					roomProviderMap=new DualHashBidiMap();
					roomProvider.put(idfProvider,roomProviderMap);		
				}
				int idfRoom=rs.getInt("idf_room");
				pensionProvierByIdfRoom.put(idfRoom,idfProvider);
				pensionProvier.put(idfProvider, resultIdfPension);
				roomProviderMap.put(rs.getString("idf_provider_room"), idfRoom);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException sqex) {
			
		}
	}

	//	@Autowired
	//	private PensionService pensionService;
	//	@Autowired
	//	private RoomService roomService;

	//	public BidiMap getPensionCode() {
	//		return pensionCode;
	//	}

	//	public Map<String, BidiMap> getRoomCode() {
	//		return roomCode;
	//	}
	//
	//	@PostConstruct
	//	public void init(){
	//		List<PensionDto> pensionList=pensionService.pensionList().getDataList();
	//		pensionCode=new DualHashBidiMap();
	//		roomCode=new HashMap<String, BidiMap>();
	//		for(PensionDto p:pensionList){
	//			if(p.getIdfProvider()!=null&&p.getIdfProvider().contains("w")){
	//				pensionCode.put(p.getIdfProvider(), p.getIdfPension());
	//				BidiMap roomMap=new DualHashBidiMap();
	//				List<RoomDto> roomList=roomService.readRoomByIdfPension(p.getIdfPension()).getDataList();
	//				for(RoomDto r:roomList){
	//					roomMap.put(r.getIdfProvider(),r.getIdfRoom());
	//				}
	//				roomCode.put(p.getIdfProvider(), roomMap);
	//			}
	//		}			
	//	}


}
