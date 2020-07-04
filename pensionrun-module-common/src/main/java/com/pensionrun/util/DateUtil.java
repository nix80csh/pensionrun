package com.pensionrun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public static int calculateDiffDay(String startDate,String endDate,String format) throws ParseException{
		SimpleDateFormat original_format = new SimpleDateFormat(format);
		Date start=original_format.parse(startDate);
		Date end=original_format.parse(endDate);
		return (int)((end.getTime()-start.getTime())/1000/60/60/24);
	}
	
	public static int calculateDiffDay(Date startDate,Date endDate) throws ParseException{
		return (int)((endDate.getTime()-startDate.getTime())/1000/60/60/24);
	}

	public static String getDateDay(String date,String format) throws Exception {
		String day = "" ;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format) ;
		Date nDate = dateFormat.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		switch(dayNum){
			case 1:
				day = "일";
				break ;
			case 2:
				day = "월";
				break ;
			case 3:
				day = "화";
				break ;
			case 4:
				day = "수";
				break ;
			case 5:
				day = "목";
				break ;
			case 6:
				day = "금";
				break ;
			case 7:
				day = "토";
				break ;
		}
		return day;
	}

	public static String getDiffDay(String date,int day,String format) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format) ;
		Date nDate = dateFormat.parse(date);
		Calendar c = Calendar.getInstance(); 
		c.setTime(nDate); 
		c.add(Calendar.DATE, day);
		nDate = c.getTime();
		return dateFormat.format(nDate);
	}	
	public static String getDiffDay(Date date,int day,String format) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format) ;
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, day);
		date = c.getTime();
		return dateFormat.format(date);
	}
	public static Date getDiffDay(Date date,int day) throws ParseException{
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, day);
		return c.getTime();
	}
	public static String dateFormatChange(String date,String oldType,String newType){
		try{
			// 포멧 변경 yyyyMMdd -> yyyy년 MM월 dd일
			SimpleDateFormat format_old_date = new SimpleDateFormat(oldType);
			SimpleDateFormat format_new_date = new SimpleDateFormat(newType);
			Date mDate = format_old_date.parse(date);
			return format_new_date.format(mDate);
		}catch (Exception e){
			return date;
		}
	}
	
	public static String dateToString(Date date,String format){
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		return transFormat.format(date);
	}
	public static List<String> getDiffDayList(String startDate,String endDate,String format)throws Exception{
		List<String> days=new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format) ;
		Date nStartDate = dateFormat.parse(startDate);
		Date nEndDate = dateFormat.parse(endDate);
		Date temp=nStartDate;
		while(temp.compareTo(nEndDate)<=0){
			System.out.println(dateFormat.format(temp));
			days.add(dateFormat.format(temp));
			Calendar c = Calendar.getInstance(); 
			c.setTime(temp); 
			c.add(Calendar.DATE, 1);
			temp = c.getTime();
		}
		return days;
	}
}
