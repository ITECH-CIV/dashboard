package org.itechciv.dashboard.impservice;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.helper.Constants;
import org.itechciv.dashboard.helper.ProcessDate;
import org.itechciv.dashboard.helper.ProcessString;
import org.itechciv.dashboard.helper.ProcessType;
import org.itechciv.dashboard.helper.ProcessCell;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.model.Regimen;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Lab;
import org.itechciv.dashboard.model.Site;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.PatientAnalyseExcelItem;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.model.VlReason;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.RegimenRepository;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.itechciv.dashboard.repository.LabRepository;
import org.itechciv.dashboard.repository.SiteRepository;
import org.itechciv.dashboard.repository.PatientRepository;
import org.itechciv.dashboard.repository.RegionRepository;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.itechciv.dashboard.repository.TestRepository;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.itechciv.dashboard.repository.VlReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

	@Autowired
	private LabRepository labRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private SiteRepository siteRepository;

	// Import Lab Data
	@Override
	public boolean storeLabImport(MultipartFile file) {

		Lab l = null;
		
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

			XSSFSheet spreadsheet = workbook.getSheetAt(0);
		
			System.out.println("LENGTH ::::: " + 	spreadsheet.getPhysicalNumberOfRows() + "\n");

			for (int i = 2; i < spreadsheet.getPhysicalNumberOfRows() +1; i++) {

				XSSFRow row = spreadsheet.getRow(i);

				if (row.getCell(1) != null) {

					l = labRepository.findLabByPrefix(row.getCell(1).getStringCellValue());

					if (l == null) {
						 l = new Lab();
						 l.setName(ProcessType.getCellStringValue(row.getCell(0)));
						 l.setPrefix(ProcessType.getCellStringValue(row.getCell(1)));
						 
				  l = labRepository.save(l);
					
					}
				}
				workbook.close();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// IMPORT LOCALITE: Region, District, Site
	@Override
	public boolean storeLocaliteImport(MultipartFile file) {
		
		Region r = null;
		District d = null;
		Site s = null;
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			
			for (int i = 7; i < spreadsheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = spreadsheet.getRow(i);

				if (row.getCell(3) != null) {

					r = regionRepository.findRegionByName(row.getCell(3).getStringCellValue());

					if (r == null) {
						Region inRegion = new Region();
						inRegion.setName(row.getCell(3).getStringCellValue());

						r = regionRepository.save(inRegion);
					}
				}

				if (row.getCell(5) != null) {

					d = districtRepository.findDistrictByName(row.getCell(5).getStringCellValue());

					if (d == null) {
						District inDistrict = new District();
						inDistrict.setName(row.getCell(5).getStringCellValue());
						inDistrict.setRegion(r);

						d = districtRepository.save(inDistrict);
					}
				}

				if (row.getCell(6) != null) {

					int cvalue = Integer.parseInt(row.getCell(8).getRawValue());
					String str = String.valueOf((int) row.getCell(6).getNumericCellValue());

					s = siteRepository.findSiteByOldCode(str);
					
					if (s == null) {
						Site inSite = new Site();
						inSite.setOldCodeSiteDHIS2(str);
						inSite.setOldSiteName(row.getCell(7).getStringCellValue());
						inSite.setUniqueSiteId(cvalue);
						inSite.setNewSiteLongName(row.getCell(9).getStringCellValue());
						inSite.setNewSiteShortName(row.getCell(10).getStringCellValue());
						inSite.setStatutId(row.getCell(11).getStringCellValue());
						inSite.setSiteCode(row.getCell(12).getStringCellValue());
						inSite.setDistrict(d);

						s = siteRepository.save(inSite);
					}
				}
				workbook.close();
			}
			
			return true;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean storeExcelImport(MultipartFile file) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean uploadTestImport(MultipartFile file) {

		Lab l = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);

			for (int i = 1; i < spreadsheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = spreadsheet.getRow(i);

				System.out.println("Nom-lab: " + row.getCell(0).getStringCellValue() + "\n");
				System.out.println("Prefix: " + row.getCell(1).getStringCellValue() + "\n");

				workbook.close();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}