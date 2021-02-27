package com.hs.mobile.gw.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommonUtils {
	
	public static String convertDateToString(String type, java.util.Date date) {
		return new SimpleDateFormat(type).format(date);
	}
	
	public static Timestamp convertStringToTimestamp(String date, String dateForm) {
		Timestamp time = null;
		try {
			DateFormat format = new SimpleDateFormat(dateForm);
			time = new Timestamp(format.parse(date).getTime());
		} catch (ParseException e) {
			Debug.trace(e.getMessage());
		}
		return time;
	}
	
	public static String replaceText(String originalText, String targetString, String replacement, boolean sensitive) {
		String replaceText = null;
		
		if(sensitive)
			replaceText = originalText.replaceAll(targetString, replacement);
		else
			replaceText = originalText.replaceAll("(?!)"+targetString, replacement);
		return replaceText;
		
	}
	
	public static boolean textMatches(String matcheText, String targetString, boolean sensitive) {
		boolean isMatche = false;
		
		if(sensitive)
			isMatche = matcheText.matches(".*"+targetString+".*");
		else
			isMatche = matcheText.matches("(?!).*"+targetString+".*");
		return isMatche;
		
	}
}
