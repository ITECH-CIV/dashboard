package org.itechciv.dashboard.iservice;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.itechciv.dashboard.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {
	
	@Override
	public Response storeFile(MultipartFile file) {
		
		Response res = new Response();
		
		try {
			
			//FileInputStream fis = new FileInputStream(new File(file.getOriginalFilename()));			
			//XSSFWorkbook  workbook = new XSSFWorkbook(fis);
			//XSSFSheet spreadsheet  = workbook.getSheetAt(0);
			//Iterator<Row> rowIterator = spreadsheet.iterator();



			
		}catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.INTERNAL_ERROR,null,ex.getMessage(), false);
		}
		return res;


	}

}
