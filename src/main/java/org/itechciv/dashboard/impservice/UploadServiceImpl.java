package org.itechciv.dashboard.impservice;


import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.model.AnalysisResult;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.AnalysisResultRepository;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.itechciv.dashboard.repository.PatientRepository;
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
	private SampleTypeRepository  sampleTypeRepository;
	
	@Autowired
	private SampleRepository  sampleRepository; 
	
	@Autowired
	private AnalysisResultRepository  analysisResultRepository;
	
	@Override
	public String storeFile(MultipartFile file) {
		
		String res = null;
		Test t = null;
		Facilitys f = null;
		Patient p = null;
		SampleType sp = null;
		Sample s = null;
		Analysis a = null;
		AnalysisResult ar = null;
		
		try {
			
			//FileInputStream fis = new FileInputStream(new File(file.getOriginalFilename()));
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

			//XSSFWorkbook  workbook = new XSSFWorkbook(fis);
			//SXSSF
			XSSFSheet spreadsheet  = workbook.getSheetAt(0);
			
			//XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            //XSSFSheet sheet = workbook.getSheetAt(0);
            var updatedRows=0;
            for(int i=1; i<spreadsheet.getPhysicalNumberOfRows();i++) {
                XSSFRow row = spreadsheet.getRow(i);
                 t = new Test();
                 f = new Facilitys();
                 
                if(row.getCell(3)!= null) {
                	t = testRepository.findTestByName(row.getCell(3).getStringCellValue());
                	if(t==null) {
                		Test inTest = new Test();
                		inTest.setStudy(row.getCell(3).getStringCellValue());;
                		t = testRepository.save(inTest);
                	}
      
                }

                if(row.getCell(7) !=  null) {
                	f = facilitysRepository.findFacilitysByCode((int)row.getCell(7).getNumericCellValue());

                	if(f==null) {
                		Facilitys inFacilitys = new Facilitys();
                		inFacilitys.setCodeSite((int)row.getCell(7).getNumericCellValue());
                		inFacilitys.setNameSite(row.getCell(8).getStringCellValue());
                		inFacilitys.setCodeSiteDatim(row.getCell(9).getStringCellValue());
                		inFacilitys.setNameSiteDatim(row.getCell(10).getStringCellValue());
                		
                		f = facilitysRepository.save(inFacilitys);
                	}
              }
                
                if(row.getCell(4)!=null && !Objects.equals(row.getCell(4).toString(), "")){
                	p = patientRepository.findPatientByCode(row.getCell(4).getStringCellValue());

                	
                	if(p==null) {
                		 Patient inPatient = new Patient();
                		 //inPatient.setSubjectno(row.getCell(2).getStringCellValue());
                		 inPatient.setSubjectid(row.getCell(4).getStringCellValue());
                		 inPatient.setGender(row.getCell(11).getStringCellValue());
            			 //p.setBirthDate(row.getCell(12).getStringCellValue());
                		 inPatient.setAgeYears((int)row.getCell(13).getNumericCellValue());
                		 inPatient.setAgeMonths((int)row.getCell(14).getNumericCellValue());
                		 inPatient.setAgeWeeks((int)row.getCell(15).getNumericCellValue());
                		 inPatient.setStatVih(row.getCell(23).getStringCellValue());
            			 //p.setArvInitDate(row.getCell(26).getStringCellValue());
                		 inPatient.setArvReg((int)row.getCell(27).getNumericCellValue());

                		 inPatient.setCurrent1(row.getCell(28).getStringCellValue());
                		 inPatient.setCurrent2(row.getCell(29).getStringCellValue());
                		 inPatient.setCurrent3(row.getCell(30).getStringCellValue());
                		 //inPatient.setCurrent4(row.getCell(31).getStringCellValue());
            			 //p.setVlPregnancy(row.getCell(42).getStringCellValue());
            			 //p.setVlSuckle(row.getCell(43).getStringCellValue());
                		 inPatient.setFacilitys(f);
            			
                		 p = patientRepository.save(inPatient);
                	}
                  			 
               }
              
               
                if(row.getCell(20)!=null){
                	
                		 a = new Analysis();
                		 a.setAnalysisStatus((int)row.getCell(19).getNumericCellValue());
            			 a.setStartedDate(LocalDateTime.parse(row.getCell(20).getStringCellValue()));
            			 a.setCompletedDate(LocalDateTime.parse(row.getCell(21).getStringCellValue()));
            			 a.setReleasedDate(LocalDateTime.parse(row.getCell(22).getStringCellValue()));
            			 a.setNamemed(row.getCell(24).getStringCellValue());
            			 a.setNameprelev(row.getCell(25).getStringCellValue());
            			 a.setVlreason(row.getCell(33).getStringCellValue());
            			 //a.setReasonother(row.getCell(34).getStringCellValue());
            			 a.setPatient(p);
            			 a.setTest(t);
            			 
                		a = analysisRepository.save(a);
                	}
                  			 
                if(row.getCell(18)!=null) {
                	sp = sampleTypeRepository.findSampleTypeByName(row.getCell(18).getStringCellValue());
                	
                	if(sp==null) {
                		SampleType inSampleType = new SampleType();
                		inSampleType.setLabel(row.getCell(18).getStringCellValue());;
                		sp = sampleTypeRepository.save(inSampleType);
                	}
      
                } 
                
                
                if(row.getCell(0)!=null){
                	s = sampleRepository.findSampleByCode(row.getCell(0).getStringCellValue());

                	if(s==null) {
                		 Sample inSample = new Sample();
                		 inSample.setLabno(row.getCell(0).getStringCellValue());
                		 inSample.setSampleStatus(row.getCell(1).getStringCellValue());
            			 //s.setDrcpt(LocalDateTime.parse(row.getCell(5).getStringCellValue()));
            			 //s.setDintv(LocalDateTime.parse(row.getCell(6).getStringCellValue()));
                		 inSample.setSampleType(sp);
                		 inSample.setAnalysis(a);
            			 
            			
                		s =sampleRepository.save(inSample);
                	}
                  			 
               }
                
                if(row.getCell(7)!=null){
            		 ar = new AnalysisResult();
            		 ar.setViralLoad(row.getCell(16).getRawValue());
            		 try {
            			 ar.setViralLoadLog(row.getCell(17).getNumericCellValue());

            		 }catch(Exception ex) {
            			 //ex.printStackTrace();
            		 }
            		
            		 ar.setAnalysis(a);
            		 ar = analysisResultRepository.save(ar);
                	}
                  			 
               
                workbook.close();
            }

			res = "Fichier enregistré avec succès";
			
           
		}catch(Exception ex) {
			
			ex.printStackTrace();
			return null;
			
		}
		return res;


	}

}
