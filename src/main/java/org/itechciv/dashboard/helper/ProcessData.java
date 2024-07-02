package org.itechciv.dashboard.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;

public class ProcessData {

    public static final String FORMAT1 = "yyyy/MM/dd HH:mm";
	public static final String FORMAT2 = "yyyy/MM/dd HH:mm:ss";
	
	
	public static String getStringValue(String field) {
		String item = field;
		if (item == null || ObjectUtils.isEmpty(item)) {
			return "";
		}
        return field; 
	}

	public static Date getDateValue(String date) {

		String dateString = date;
		Date convertedDate = null;

		if (dateString == null || ObjectUtils.isEmpty(dateString)) {
			return null;
		}

        if(dateString != null || ObjectUtils.isNotEmpty(dateString)){
			try {
				SimpleDateFormat formatterOne = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
				convertedDate = formatterOne.parse(dateString);
			}catch(Exception ex){
				try{
				SimpleDateFormat formatterTwo = new SimpleDateFormat("dd-MMM-YY", Locale.FRENCH);
				convertedDate = formatterTwo.parse(dateString);
				}catch(Exception e){
					return null;
				}
			}
		}
		return convertedDate;
	} 

	public static int getIntValue(String s){

		String value = s; 

		int number;

		try{

        number = Integer.valueOf(value);
		return number;
	
		}catch(NumberFormatException e){
			System.out.println("Error:" +e.getMessage());
			return 0;
		}

	}

public static String concatenateCurrentValue(String current1, String current2, String current3) {
	
	String value1 = current1;
	String value2 = current2;
	String value3 = current3;
	
	if (value1 == null ||  ObjectUtils.isEmpty(value1)   || 
	    value2 == null ||  ObjectUtils.isEmpty(value2)  ||
		value3 == null ||  ObjectUtils.isEmpty(value3)) {
		return "";
	}
	
    String str3 = (value3.trim().length() >3)? value3.trim().substring(0, 3): value3.trim();
    String str2 = (value2.trim().length() >3)? value2.trim().substring(0, 3): value3.trim(); 
    
    String str1 = (value1.trim().length() >3)? value1.trim().substring(0, 4): value1.trim();
    
    return str3 + " " + str2 + " " + str1; 
    
	} 

	
  public static String labNoSubValue(String s) {
	
	String current = s;
	
	if (current == null ||  ObjectUtils.isEmpty(current)) {
		return "";
	}
	
    String value = current.substring(0, 1);
    
    return value;
    
	}
}
