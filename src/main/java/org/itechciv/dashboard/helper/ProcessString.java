package org.itechciv.dashboard.helper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ProcessString {
	
	public static List<String> categoryAge(String[] ages)  
	
	{  
		List<String> listCategory = new ArrayList<>();
		
       for(int i=0; i<ages.length; i++) {
    	   
    	   String[] items = ages[i].split("-");
    	   
    	   System.out.println(items[0] +" " + items[1]);
    	   
    	   listCategory.add(items[i]);
       }
       return listCategory;
		
	}  
	
public static String concatenateCurrentValue(String current1, String current2, String current3, String current4) {
		
		return "";	
	}

}
