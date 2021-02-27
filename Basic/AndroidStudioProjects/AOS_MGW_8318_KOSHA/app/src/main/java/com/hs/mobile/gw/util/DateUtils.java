package com.hs.mobile.gw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	public static DateFormat todayDateFormat  = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
	public static DateFormat pastDateFormat  = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
	public static SimpleDateFormat gwFullDateFormat  = new SimpleDateFormat("yyyyMMdd HHmmss");
	public static SimpleDateFormat gWDateFormat  = new SimpleDateFormat("yyyyMMdd");	
	public static SimpleDateFormat gwSignFullDateFormat  = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	public static SimpleDateFormat gWSignDateFormat  = new SimpleDateFormat("yyyy.MM.dd");	
	public static SimpleDateFormat gWSignSearchDateFormat  = new SimpleDateFormat("yyyy-MM-dd");	
	public static String getDateStringFromGWDate(String str){
		String returnDateString = null;
		try{
			String[] data = str.split(" ");
			String dateSrting = data[0];			
						
			Date today = gWDateFormat.parse(gWDateFormat.format(new Date())); // 오늘 날짜(00:00:00)
			Date inputDate;
			Date inputFullDate;
			inputDate = gWDateFormat.parse(dateSrting);
			inputFullDate = gwFullDateFormat.parse(str);
			
			if(inputDate.getTime() < today.getTime()){ // 오늘 이전이면 - 년.월.일
				returnDateString = pastDateFormat.format(inputDate);
			}else{ //오늘이면 - 시:분
				returnDateString = todayDateFormat.format(inputFullDate);
			}
		}catch(ParseException e){
			Debug.trace(e.getMessage());
			return str;
		}catch(Exception e){
			Debug.trace(e.getMessage());
			return str;
		}
		
		return returnDateString;
	}
	
	public static String getTodayDate(){
		String returnDateString = null;
		returnDateString = gWSignSearchDateFormat.format(new Date());
		Debug.trace(returnDateString);
		return returnDateString;
	}
	
	public static String getYearMiner() {
		String returnDateString = null;
		Calendar cal = Calendar.getInstance();
		cal.add(cal.YEAR, -1);
		
		String  thisYearMiner = cal.get(cal.YEAR)+"-"+(cal.get(cal.MONTH)+1)+"-"+cal.get(cal.DATE);
		returnDateString = thisYearMiner;
//			Date date = gWSignSearchDateFormat.parse(thisYearMiner);
//			returnDateString = gWSignSearchDateFormat.format(date);
		Debug.trace(returnDateString);
		return returnDateString;
	}
}
