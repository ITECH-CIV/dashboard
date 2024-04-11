package org.itechciv.dashboard.helper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessDate {
	
	public static final String FORMAT1 = "yyyy/MM/dd HH:mm";
	public static final String FORMAT2 = "yyyy/MM/dd HH:mm:ss";

	
	public void AgeCalculators(String birthDate) {
		/*
		 * String birthDateString = birthDate; DateTimeFormatter dateFormatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd"); DateTime d =
		 * DateTime.parse(birthDateString, dateFormatter);
		 * 
		 * DateTime currentDate = DateTime.now();
		 * 
		 * Years age = Years.yearsBetween(birthDate, currentDate);
		 * 
		 * System.out.println("Birthdate: " + birthDateString);
		 * System.out.println("Current Date: " + currentDate.toString(dateFormatter));
		 * System.out.println("Age: " + age.getYears() + " years");
		 */
	    System.out.println(" Affichage : +"); 
		 
	}
	
	public static int calculateAge(LocalDate dob)   
	{  
      LocalDate curDate = LocalDate.now();  
      if ((dob != null) && (curDate != null))   
	     {  
	     return Period.between(dob, curDate).getYears();  
	     }  
	  else  
	  {  
	  return 0;  
	  }  
	}  
	
	public class AgeCalculator {

	    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
	        if ((birthDate != null) && (currentDate != null)) {
	            return Period.between(birthDate, currentDate).getYears();
	        } else {
	            return 0;
	        }
	    }
	} 
	

	public static Date getCellDateValue(FormulaEvaluator evaluator, XSSFCell cel) {
		Cell cell = cel;
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return null;
		}
		if (cell.getCellType() == CellType.STRING) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
				return sdf.parse(cell.getStringCellValue());
			} catch (Exception ex) {
				try {
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-YY", Locale.FRENCH);
					return sdf1.parse(cell.getStringCellValue());
				} catch (Exception ex1) {
					return null;
				}
			}
		}
		double valDouble = evaluator.evaluate(cell).getNumberValue();
		return ((int) (valDouble) == 0) ? null : DateUtil.getJavaDate(valDouble);
	}
	
	//Convert java.util.Date to java.time.LocalDate 
	public static LocalDate toLocalDate(Date date) {
		try {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


 public static LocalDateTime toLocalDateTime(FormulaEvaluator evaluator, XSSFCell cel) {
	 
	 Cell cell = cel;
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return null;
		}
		
		final String fmt1 = FORMAT1;
		final String fmt2 = FORMAT2;


		if (cell.getCellType() == CellType.STRING) {
			try {
				DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(fmt1);

				return LocalDateTime.parse(cell.getStringCellValue(),  dtf1);
			} catch (Exception ex) {
				try {
					DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(fmt2);

					return LocalDateTime.parse(cell.getStringCellValue(),  dtf2);
				} catch (Exception ex1) {
					return null;
				}
			}
		}
		
		double valDouble = evaluator.evaluate(cell).getNumberValue();
		return ((int) (valDouble) == 0) ? null : DateUtil.getLocalDateTime(valDouble);	
  }
	 
 }
