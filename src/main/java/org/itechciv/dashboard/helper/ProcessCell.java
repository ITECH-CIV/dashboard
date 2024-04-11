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
	
	
}
