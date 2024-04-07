package org.itechciv.dashboard.impservice;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.helper.Constants;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.DietRepository;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.itechciv.dashboard.repository.PatientRepository;
import org.itechciv.dashboard.repository.RegionRepository;
import org.itechciv.dashboard.repository.SampleRepository;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.itechciv.dashboard.repository.TestRepository;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.itechciv.dashboard.repository.VlReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private FacilitysRepository facilitysRepository;
	
	@Autowired
	private PatientRepository  patientRepository;
	
	@Autowired
	private AnalysisRepository  analysisRepository;
	
	@Autowired
	private SampleTypeRepository sampleTypeRepository;
	
	@Autowired
	private SampleRepository  sampleRepository; 
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private DietRepository dietRepository;
	
	@Autowired
	private VihTypeRepository vihTypeRepository;
	
	@Autowired
	private VlReasonRepository vlReasonRepository;
	
	private final DataFormatter dataFormatter = new DataFormatter();
	
	private final DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
	
	//IMPORT LOCALITE: Region, District, Facilitys
	@Override
	public boolean storeLocaliteImport(MultipartFile file) {
		
		Region r = null;
		District d = null;
		Facilitys f = null;
						 
		   try {
			   
			   XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

				XSSFSheet spreadsheet  = workbook.getSheetAt(0);
				
				 for(int i=7; i<spreadsheet.getPhysicalNumberOfRows();i++) {
					 
					 XSSFRow row = spreadsheet.getRow(i);
					 
					 if(row.getCell(3)!= null) {
						 
					     r = regionRepository.findRegionByName(row.getCell(3).getStringCellValue());
					     
					     if(r==null) { 
						 Region inRegion = new Region();
						 inRegion.setName(row.getCell(3).getStringCellValue());
						 
						  r = regionRepository.save(inRegion); 
						 } 
			            }
					 
					 if(row.getCell(5) !=  null) {
						 
					     d = districtRepository.findDistrictByName(row.getCell(5).getStringCellValue());
					     
					     if(d==null) { 
							 District inDistrict = new District();
							 inDistrict.setName(row.getCell(5).getStringCellValue());
							 inDistrict.setRegion(r);

						  d = districtRepository.save(inDistrict); 
						 }  	
		              }
					 
                     if(row.getCell(6) !=  null) {
						 
						 int cvalue = Integer.parseInt(row.getCell(8).getRawValue());
						 String str = String.valueOf((int)row.getCell(6).getNumericCellValue());

					     f = facilitysRepository.findFacilitysByOldCode(str);
					     if(f==null) { 
							 Facilitys inFacilitys = new Facilitys();
							 inFacilitys.setOldCodeFacilitysDHIS2(str);
							 inFacilitys.setOldFacilityName(row.getCell(7).getStringCellValue());
							 inFacilitys.setUniqueFacilitysId(cvalue);
							 inFacilitys.setNewFacilitysLongName(row.getCell(9).getStringCellValue());
							 inFacilitys.setNewFacilitysShortName(row.getCell(10).getStringCellValue());
							 inFacilitys.setStatutId(row.getCell(11).getStringCellValue());
							 inFacilitys.setFacilitysCode(row.getCell(12).getStringCellValue());
							 inFacilitys.setDistrict(d);

						  f = facilitysRepository.save(inFacilitys); 
						 }  	
		              } 	 
	               workbook.close(); 
				 }
			   return true;
		   }catch(Exception ex) {
			  ex.printStackTrace(); 
			  System.out.println("Exception => " + ex.getMessage());
			  return false;
		   }		   
	}

	@Override
	public boolean storeExcelImport(MultipartFile file) {
		
		Test t = null;
		Facilitys f = null;
		
		try { 
			
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet  = workbook.getSheetAt(0);

			 for(int i=7; i<spreadsheet.getPhysicalNumberOfRows();i++) {
	              XSSFRow row = spreadsheet.getRow(i);
	              
	              t = new Test();
	              
	            if(row.getCell(3)!=null) {
            	  t = testRepository.findTestByName(row.getCell(3).getStringCellValue());
	                
	                if(t==null) {
	                	Test inTest = new Test();
	                	inTest.setName(Constants.TEST_NAME);
	                	inTest.setStudy(row.getCell(3).getStringCellValue());
	                	
	                	t = testRepository.save(inTest);
	                } 
	              }
	            
	            if(row.getCell(7) != null) {
	            	String str = String.valueOf(row.getCell(7).getRawValue());
	            	
	            	try {
					     f = facilitysRepository.findFacilitysByOldCode(str);
					     //Si facilitys est null  
					     if(f==null) {
				    		Facilitys inFacilitys = new Facilitys();
				    		 try {
				    			 inFacilitys.setOldCodeFacilitysDHIS2(str);
	                		 }catch(Exception ex) {
	              			       System.out.println("Exception => " + ex.getMessage());     
	                		 }	                		
	                		inFacilitys.setNameSite(row.getCell(8).getStringCellValue()); 
	                		try {
				    			 inFacilitys.setCodeSiteDatim(row.getCell(9).getStringCellValue());
	                		 }catch(Exception ex) {
	              			       System.out.println("Exception => " + ex.getMessage());     
	                		 }
	                		try {
				    			 inFacilitys.setNameSiteDatim(row.getCell(10).getStringCellValue());
	                		 }catch(Exception ex) {
	              			       System.out.println("Exception => " + ex.getMessage());     
	                		 }
	                	    f =  facilitysRepository.save(inFacilitys);              		
					     }else {
					    	 //Si facilitys existe alors faire une mise à jour à partir du fichier
					    	 f.setNameSite(row.getCell(8).getStringCellValue());
					    	 f.setCodeSiteDatim(row.getCell(9).getStringCellValue());
					    	 f.setNameSiteDatim(row.getCell(10).getStringCellValue());
					    	
					    	f = facilitysRepository.saveAndFlush(f); 
					     }  
	            	}catch(Exception ex) {
	            		ex.printStackTrace();
	            	}
	            }	
/**********************************************LIMIT ***************************************/	            
                workbook.close();
			 }
            return true;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception => " + ex.getMessage());
			return false;
		}		
	}
/*******************************************FIN**********************************************/

	@Override
	public boolean uploadTestImport(MultipartFile file) {
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet  = workbook.getSheetAt(0);
			
			 for(int i=7; i<spreadsheet.getPhysicalNumberOfRows();i++) {
	              XSSFRow row = spreadsheet.getRow(i);
	              
	             if(row.getCell(12) != null) {
	            	  
            	    if(row.getCell(12).getCellType() == CellType.STRING){
            	    	
            	    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            	    	String dateInString = row.getCell(12).getStringCellValue();
            	    	
            	    	try {
            	    		Date date = formatter.parse(dateInString);
            	    		System.out.println("simple-date: " + date + "\n");
            	    		System.out.println("formated-date: " +formatter.format(date));	
            	    	}catch(Exception ex) {
            	    		ex.printStackTrace();
            	    	}
            	    }
            	} 
	             
	             if(row.getCell(5).getCellType() == CellType.STRING) {
	            	 String dateInString = row.getCell(5).getStringCellValue();
         	         DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.FRENCH); 
         	    	
	            	 
	            	 
	            	 
	            	 
         		    System.out.println ("Value-localdatetime: " +row.getCell(5).getStringCellValue() + "\n");

	             }
	             /*************************************FIN****************************************************/	              
	              workbook.close();
	              }
				return true;	
			 }catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}