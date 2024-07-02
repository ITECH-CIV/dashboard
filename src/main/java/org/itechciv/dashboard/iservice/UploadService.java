package org.itechciv.dashboard.iservice;

import java.util.List;

import org.itechciv.dashboard.helper.CategoryAge;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	boolean storeLocaliteImport(MultipartFile file); 
	boolean storeExcelImport(MultipartFile file);
	boolean storeCsvImport(MultipartFile file);
	boolean uploadTestImport(MultipartFile file);
	
	//boolean storeExcelImport2(MultipartFile file);
	
	boolean storeLabImport(MultipartFile file);
	
	boolean storePartnerImport(MultipartFile file);
	
	public boolean updateSite(MultipartFile file);

	List<CategoryAge> getCDCAgeCategory();
	List<CategoryAge> getNationalAgeCategory(); 

	Integer getCDCAgeCategorieId(Integer age);
	Integer getNationalCategoriesId(Integer age);

}
