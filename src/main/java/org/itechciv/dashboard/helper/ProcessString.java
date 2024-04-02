package org.itechciv.dashboard.helper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessString {
	
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
