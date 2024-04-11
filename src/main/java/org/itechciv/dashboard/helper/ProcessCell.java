package org.itechciv.dashboard.helper;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;


@Service
@Transactional
public class ProcessCell {
	
	public void splitString(String chaine) {
		
		//List<String> result = new ArrayList<>();
		//String results[] = new String[2];
		String intervalle = chaine;
		String[] parts = intervalle.split("-");
		
		for(String part: parts) {
			System.out.printf("Affichage: +", part);
		}	
	}
	
	public static String getCellStringValue(XSSFCell cel) {
		Cell cell = cel;
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return "";
		}
		DataFormatter df = new DataFormatter();
		return df.formatCellValue(cell).trim();
	}
	
	public static String concatenateCurrentValue(String current1, String current2, String current3, String current4) {
		
		return "";	
	}
	
	public static Double getCellDoubleValue(XSSFCell cel) {
		Cell cell = cel;
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return 0.0;
		}
		if (cell.getCellType() == CellType.STRING) {
			String v = cell.getStringCellValue();
			if (v.trim().isEmpty()) {
				return 0.0;
			} else {
				v = v.replace(",", ".").replace("%", "");
				Pattern decimalNumPattern = Pattern.compile("\\d+(\\.\\d+)?");
				Matcher matcher = decimalNumPattern.matcher(v);
				return (matcher.find()) ? Double.valueOf(Double.parseDouble(matcher.group())) : 0.0;
			}
		}
		return Double.valueOf(cell.getNumericCellValue());
	}

}
