package org.itechciv.dashboard.impservice;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.itechciv.dashboard.repository.PatientRepository;
import org.itechciv.dashboard.repository.RegionRepository;
import org.itechciv.dashboard.repository.SampleRepository;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.itechciv.dashboard.repository.TestRepository;
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
	
	//private final DataFormatter dataFormatter = new DataFormatter();
	
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
		// TODO Auto-generated method stub
		return false;
	}

}