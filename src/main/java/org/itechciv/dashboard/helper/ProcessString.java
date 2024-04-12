package org.itechciv.dashboard.helper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

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
	
public static String concatenateCurrentValue(XSSFCell current3, XSSFCell current2, XSSFCell current1) {
	
	Cell cel3 = current3;
	Cell cel2 = current2;
	Cell cel1 = current1;
	String molecule = "";
	
	if (cel3 == null || cel3.getCellType() == CellType.BLANK   || 
		cel2 == null || cel2.getCellType() == CellType.BLANK   ||
		cel1 == null || cel1.getCellType() == CellType.BLANK) {
		return "";
	}
	
	if (cel3 == null || cel3.getCellType() == CellType.NUMERIC       || 
		cel2 == null || cel2.getCellType() == CellType.NUMERIC   ||
		cel1 == null || cel1.getCellType() == CellType.NUMERIC) {
			return "";
		}
	
    String str3 = (cel3.getStringCellValue().trim().length() >3)? cel3.getStringCellValue().trim().substring(0, 3): cel3.getStringCellValue().trim();
    String str2 = (cel2.getStringCellValue().trim().length() >3)? cel2.getStringCellValue().trim().substring(0, 3): cel2.getStringCellValue().trim(); 
    
    String str1 = (cel1.getStringCellValue().trim().length() >3)? cel1.getStringCellValue().trim().substring(0, 5): cel1.getStringCellValue().trim();
    
    return str3 + " " + str2 + " " + str1; 
    
	}

}
