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
import org.itechciv.dashboard.model.Site;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.PatientAnalyseExcelItem;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.model.VlReason;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.RegimenRepository;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.itechciv.dashboard.repository.SiteRepository;
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
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AnalysisRepository analysisRepository;

	@Autowired
	private SampleTypeRepository sampleTypeRepository;

	@Autowired
	private SampleRepository sampleRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RegimenRepository regimenRepository;

	@Autowired
	private VihTypeRepository vihTypeRepository;

	@Autowired
	private VlReasonRepository vlReasonRepository;

	private final DataFormatter dataFormatter = new DataFormatter();

	private final DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

	static XSSFCell cell;

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
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception => " + ex.getMessage());
			return false;
		}
	}
	

	@Override
	public boolean storeExcelImport(MultipartFile file) {

		Test t = null;
		Site s = null;
		Patient p = null;
		VihType vt = null;
		VlReason vr = null;
		Analysis a = null;
		SampleType st = null;
		Sample sp = null;
		Regimen reg = null;
		Regimen inReg = null;

		try {
			System.out.println("Hello world");

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			for (int i = 1; i < spreadsheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = spreadsheet.getRow(i);
				// Test
				if (row.getCell(3) != null) {

					t = testRepository.findTestByName(row.getCell(3).getStringCellValue());
					System.out.println("Test:" + t + "\n");

					if (t == null) {
						Test inTest = new Test();
						inTest.setName(Constants.TEST_NAME);
						inTest.setStudy(row.getCell(3).getStringCellValue());

						t = testRepository.save(inTest);
						System.out.println("Test:" + t.toString());
					}
				}
				// Site
				if (row.getCell(7) != null) {
					String str = String.valueOf(row.getCell(7).getRawValue());

					s = siteRepository.findSiteByOldCode(str);

					if (s == null) {
						Site inSite = new Site();
						try {
							inSite.setOldCodeSiteDHIS2(str);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						inSite.setNameSite(row.getCell(8).getStringCellValue());
						inSite.setCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(9)));
						inSite.setNameSiteDatim(ProcessType.getCellStringValue(row.getCell(10)));

						s = siteRepository.save(inSite);

						// System.out.println("inserted-facilitys:" +f.getId()+"\n");

					} else {
						s.setNameSite(row.getCell(8).getStringCellValue());
						s.setCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(9)));
						s.setNameSiteDatim(ProcessType.getCellStringValue(row.getCell(10)));

						s = siteRepository.saveAndFlush(s);

						// System.out.println("updated-facilitys:" +f.getId()+"\n");
					}
				}
				// Patient
				if (row.getCell(4) != null) {
					p = patientRepository.findPatientByCode(row.getCell(4).getRawValue());

					if (p == null) {
						Patient inPatient = new Patient();
						inPatient.setSubjectno(ProcessType.getCellStringValue(row.getCell(2)));
						inPatient.setSubjectid(ProcessType.getCellStringValue(row.getCell(4)));
						inPatient.setGender(row.getCell(11).getStringCellValue());
						inPatient.setBirthDate(ProcessType.getCellDateValue(evaluator, row.getCell(12)));
						inPatient.setAgeYears((int) row.getCell(13).getNumericCellValue());
						inPatient.setAgeMonths((int) row.getCell(14).getNumericCellValue());
						inPatient.setAgeWeeks((int) row.getCell(15).getNumericCellValue());
						inPatient.setArvInitDate(ProcessType.getCellDateValue(evaluator, row.getCell(26)));
						inPatient.setSite(s);

						p = patientRepository.save(inPatient);
					}
				}
				// VihType
				if (row.getCell(23) != null) {

					vt = vihTypeRepository.findVihTypeByName(ProcessType.getCellStringValue(row.getCell(23)));

					if (vt == null) {
						VihType inVihType = new VihType();
						inVihType.setName(ProcessType.getCellStringValue(row.getCell(23)));

						vt = vihTypeRepository.save(inVihType);
					}
				}
				// VlReason
				if (row.getCell(33) != null) {

					vr = vlReasonRepository.findVlReasonByName(ProcessType.getCellStringValue(row.getCell(33)));

					if (vr == null) {
						VlReason inVlReason = new VlReason();
						inVlReason.setName(ProcessType.getCellStringValue(row.getCell(33)));

						vr = vlReasonRepository.save(inVlReason);
					}
				} 
				// Regimen
				String molecule = ProcessString.concatenateCurrentValue(row.getCell(28), row.getCell(29), row.getCell(30));
				System.out.println("concatenateCurrentValue ::: " + molecule + "\n");
				reg = regimenRepository.findRegimenByName(molecule);
				
				boolean check = (reg == null);
				if (check) {
					inReg = regimenRepository.findRegimenByName(Constants.DIET_NAME_OTHER);
					reg = inReg;
					System.out.println("regimen-other:" + inReg.getName() + "\n");
				}
				System.out.println("regimen-exists:" + reg.getName() + "\n");

				
				// Sample type
				if (row.getCell(18) != null) {
					st = sampleTypeRepository.findSampleTypeByName(row.getCell(18).getStringCellValue());

					if (st == null) {
						SampleType inSampleType = new SampleType();
						inSampleType.setLabel(row.getCell(18).getStringCellValue());

						st = sampleTypeRepository.save(inSampleType);
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

	
	
}