package org.itechciv.dashboard.iservice;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.itechciv.dashboard.service.FacilitysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Iterator;

@Service
@Transactional
public class FacilitysServiceImpl extends GenericServiceImpl<Facilitys, Long> implements FacilitysService{

	@Autowired
	private FacilitysRepository facilitysRepo;
	
	
	@Override
	public Response storeFacilitysFile(MultipartFile file) {
		// TODO Auto-generated method stub

				Response res = new Response();
				
				List<Facilitys> listFacilitys = new ArrayList<Facilitys>();
				Facilitys facilitys;  
				
				try 
				{
					
					FileInputStream fis = new FileInputStream(new File(file.getOriginalFilename()));
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet spreadsheet = workbook.getSheetAt(0);
					Iterator<Row> rowIterator = spreadsheet.iterator();
					
					while (rowIterator.hasNext())
					{
						facilitys = new Facilitys();
			               						
						Row row = rowIterator.next();

			            if (row.getRowNum() == 0) {
			                continue;
			            }

			            Iterator<Cell> cellIterator = row.cellIterator();
			            
			            while (cellIterator.hasNext()) {
			                Cell cell = cellIterator.next();
			                
			                if (cell.getColumnIndex() == 0) {
			                	
			                
			                } 
			                   
		                }
			              
					}
					
					fis.close();

					res = new Response(ResponseStatusEnum.SUCCESS, listFacilitys, "Facilitys enregistré avec succès", true); 
				} catch(Exception ex) {
					res =  new  Response(ResponseStatusEnum.INTERNAL_ERROR,null,ex.getMessage(), false);
				}
				return res;
				
			}
	

	@Override
	public Facilitys getByCode(String code) {
		
		Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByCode(code);
			
			if( f!=null)
			{ 
				return f;
			} 
			else 
			{ 
				return null;
			}
		}
		catch(Exception ex) { 
			ex.printStackTrace();
			return null;
		}	
	}
}
