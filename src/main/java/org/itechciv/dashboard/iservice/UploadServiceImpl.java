package org.itechciv.dashboard.iservice;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.model.AnalysisResult;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.itechciv.dashboard.repository.TestRepository;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.itechciv.dashboard.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {
	
	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private FacilitysRepository facilitysRepository;
	
	@Override
	public Response storeFile(MultipartFile file) {
		
		Response res = new Response();
		Test t = null;
		Facilitys f = null;
		Patient p = null;
		SampleType sp = null;
		Sample s = null;
		Analysis a = null;
		AnalysisResult ar = null;
		
		
		try {
			
			FileInputStream fis = new FileInputStream(new File(file.getOriginalFilename()));			
			XSSFWorkbook  workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet  = workbook.getSheetAt(0);
			
			//XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            //XSSFSheet sheet = workbook.getSheetAt(0);
            var updatedRows=0;
            for(int i=8; i<spreadsheet.getPhysicalNumberOfRows();i++) {
                XSSFRow row = spreadsheet.getRow(i);
                 t = new Test();
                 f = new Facilitys();
                 
                if(row.getCell(3)!=null && !Objects.equals(row.getCell(3).toString(), "")) {
                	t = testRepository.findTestByName(row.getCell(3).getStringCellValue());
                	
                	if(t==null) {
                		t.setStudy(row.getCell(3).getStringCellValue());;
                		testRepository.save(t);
                	}
      
                }else if(row.getCell(7)!=null && !Objects.equals(row.getCell(7).toString(), "")) {
                	f = facilitysRepository.findFacilitysByCode(row.getCell(3).getStringCellValue());
                	
                	if(f==null) {
                		f.setCodeSite(row.getCell(7).getStringCellValue());
                		f.setNameSite(row.getCell(8).getStringCellValue());
                		f.setCodeSiteDatim(row.getCell(9).getStringCellValue());
                		f.setCodeSite(row.getCell(10).getStringCellValue());
                		
                		facilitysRepository.save(f);
                	}
     	
                }else if(row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "")
                		 && row.getCell(4)!=null && !Objects.equals(row.getCell(4).toString(), "",
                		 &&	row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 &&  row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 && row.getCell(2)!=null && !Objects.equals(row.getCell(2).toString(), "",
                		 		 
                				 
                		)
            }
			
			
			



			
		}catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.INTERNAL_ERROR,null,ex.getMessage(), false);
		}
		return res;


	}

}
