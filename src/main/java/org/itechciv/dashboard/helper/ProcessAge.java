package org.itechciv.dashboard.helper;

import java.time.LocalDate;
import java.time.Period;

public class ProcessAge {
	
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

}
