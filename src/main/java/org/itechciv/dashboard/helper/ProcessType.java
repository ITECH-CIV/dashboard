package org.itechciv.dashboard.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class ProcessType {
	
	public static LocalDate toLocalDate(Date date) {
		try {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Double getCellDoubleValue(Row row, int column) {
		Cell cell = row.getCell(column);
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
	

	public static String getCellStringValue(Row row, int column) {
		Cell cell = row.getCell(column);
		if (cell == null || cell.getCellType() == CellType.BLANK) {
			return "";
		}
		DataFormatter df = new DataFormatter();
		return df.formatCellValue(cell).trim();
	}


}
