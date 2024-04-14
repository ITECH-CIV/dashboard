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
import org.itechciv.dashboard.model.Diet;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.PatientAnalyseExcelItem;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.model.VlReason;
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
	private FacilitysRepository facilitysRepository;

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
	private DietRepository dietRepository;

	@Autowired
	private VihTypeRepository vihTypeRepository;

	@Autowired
	private VlReasonRepository vlReasonRepository;

	private final DataFormatter dataFormatter = new DataFormatter();

	private final DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

	static XSSFCell cell;

	// IMPORT LOCALITE: Region, District, Facilitys
	@Override
	public boolean storeLocaliteImport(MultipartFile file) {

		Region r = null;
		District d = null;
		Facilitys f = null;

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

					f = facilitysRepository.findFacilitysByOldCode(str);
					if (f == null) {
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
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception => " + ex.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean storeExcelImport2(MultipartFile file)
	{
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			List<PatientAnalyseExcelItem> excelItems = new ArrayList<PatientAnalyseExcelItem>();
			for (int i = 1; i < spreadsheet.getPhysicalNumberOfRows(); i++) 
			{
				PatientAnalyseExcelItem excelItem = new PatientAnalyseExcelItem(spreadsheet.getRow(i));
				excelItems.add(excelItem);
			}
			workbook.close();
			
			List<Test> processedTests = new ArrayList<Test>();
			for(var excelItem: excelItems)
			{
				var codeTest = excelItem.getSTUDY();
				
				Test test = null;
				if (codeTest != null && !codeTest.isEmpty()) {
					test = testRepository.findTestByName(codeTest);
					if (test == null) {
						test = new Test();
						test.setName(codeTest == "VLS" ? Constants.TEST_NAME : codeTest);
						test.setStudy(codeTest);
						test = testRepository.save(test);
					}
				}
			}
			
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean storeExcelImport(MultipartFile file) {

		Test t = null;
		Facilitys f = null;
		Patient p = null;
		VihType vt = null;
		VlReason vr = null;
		Analysis a = null;
		SampleType st = null;
		Sample s = null;
		Diet d = null;
		Diet inDiet = null;

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
//						inTest.setName(Constants.TEST_NAME);
						inTest.setStudy(row.getCell(3).getStringCellValue());

						t = testRepository.save(inTest);
						System.out.println("Test:" + t.toString());
					}
				}
				// Facilitys
				if (row.getCell(7) != null) {
					String str = String.valueOf(row.getCell(7).getRawValue());

					f = facilitysRepository.findFacilitysByOldCode(str);

					if (f == null) {
						Facilitys inFacilitys = new Facilitys();
						try {
							inFacilitys.setOldCodeFacilitysDHIS2(str);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						inFacilitys.setNameSite(row.getCell(8).getStringCellValue());
						inFacilitys.setCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(9)));
						inFacilitys.setNameSiteDatim(ProcessType.getCellStringValue(row.getCell(10)));

						f = facilitysRepository.save(inFacilitys);

						// System.out.println("inserted-facilitys:" +f.getId()+"\n");

					} else {
						f.setNameSite(row.getCell(8).getStringCellValue());
						f.setCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(9)));
						f.setNameSiteDatim(ProcessType.getCellStringValue(row.getCell(10)));

						f = facilitysRepository.saveAndFlush(f);

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
						inPatient.setFacilitys(f);

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
				// Diet
				/*
				 * XSSFCell current3 = null; XSSFCell current2 = null; XSSFCell current1 = null;
				 * 
				 * String molecule = ProcessString.concatenateCurrentValue(current3, current2,
				 * current1);
				 * 
				 * d = dietRepository.findDietByName(molecule);
				 * 
				 * if (d == null) {
				 * 
				 * inDiet = dietRepository.findDietByName(Constants.DIET_NAME_OTHER); d =
				 * inDiet; }
				 */
				
				// Diet
				String molecule = ProcessString.concatenateCurrentValue(row.getCell(28), row.getCell(29), row.getCell(30));
				System.out.println("concatenateCurrentValue ::: " + molecule + "\n");
				d = dietRepository.findDietByName(molecule);
				
				boolean check = (d == null);
				if (check) {
					inDiet = dietRepository.findDietByName(Constants.DIET_NAME_OTHER);
					d = inDiet;
					System.out.println("diet-other:" + d.getName() + "\n");
				}
				System.out.println("diet-exists:" + d.getName() + "\n");

				// Analysis
				if (row.getCell(19) != null) {

					String str = String.valueOf(row.getCell(16).getRawValue());

					a = new Analysis();
					a.setAnalysisStatus((int) row.getCell(19).getNumericCellValue());
					a.setStartedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(20)));
					a.setCompletedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(21)));
					a.setReleasedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(22)));
					a.setViralLoad(str);
					a.setViralLoadLog(ProcessType.getCellDoubleValue(row.getCell(17)));
					a.setReasonother(ProcessType.getCellStringValue(row.getCell(34)));
					a.setPatient(p);
					a.setTest(t);
					a.setVlReason(vr);
					a.setVihType(vt);
					a.setDiet(d);
					
					a = analysisRepository.save(a);
				}
				// Sample type
				if (row.getCell(18) != null) {
					st = sampleTypeRepository.findSampleTypeByName(row.getCell(18).getStringCellValue());

					if (st == null) {
						SampleType inSampleType = new SampleType();
						inSampleType.setLabel(row.getCell(18).getStringCellValue());

						st = sampleTypeRepository.save(inSampleType);
					}
				}
				// Sample
				if (row.getCell(0) != null) {
					s = sampleRepository.findSampleByCode(row.getCell(0).getStringCellValue());

					if (s == null) {
						Sample inSample = new Sample();
						inSample.setLabno(row.getCell(0).getStringCellValue());
						inSample.setSampleStatus(row.getCell(1).getStringCellValue());
						inSample.setDrcpt(ProcessType.toLocalDateTime(evaluator, row.getCell(5)));
						inSample.setDintv(ProcessType.toLocalDateTime(evaluator, row.getCell(6)));
						inSample.setSampleType(st);
						inSample.setAnalysis(a);

						s = sampleRepository.save(inSample);
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

	/*******************************************
	 * FIN
	 **********************************************/

	@Override
	public boolean uploadTestImport(MultipartFile file) {

		Patient p = null;
		VihType vt = null;
		VlReason vr = null;
		Analysis a = null;
		SampleType st = null;
		Sample s = null;
		Diet d = null;
		Diet inDiet = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			// int cpt = 0;
			for (int i = 7; i < spreadsheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = spreadsheet.getRow(i);

				if (row.getCell(4) != null) {

					p = new Patient();

					p.setSubjectno(ProcessType.getCellStringValue(row.getCell(2)));
					p.setSubjectid(ProcessType.getCellStringValue(row.getCell(4)));
					p.setGender(row.getCell(11).getStringCellValue());
					p.setBirthDate(ProcessType.getCellDateValue(evaluator, row.getCell(12)));
					p.setAgeYears((int) row.getCell(13).getNumericCellValue());
					p.setAgeMonths((int) row.getCell(14).getNumericCellValue());
					p.setAgeWeeks((int) row.getCell(15).getNumericCellValue());
					p.setArvInitDate(ProcessType.getCellDateValue(evaluator, row.getCell(26)));

					System.out.println("Patient-object:" + p.getBirthDate() + "\n");
				}

				// Diet
				String molecule = ProcessString.concatenateCurrentValue(row.getCell(28), row.getCell(29), row.getCell(30));
				System.out.println("concatenateCurrentValue ::: " + molecule + "\n");
				d = dietRepository.findDietByName(molecule);
				//System.out.println("diet-values:" + d.getName() + "\n" );
				boolean check = (d == null);
				if (check) {
					inDiet = dietRepository.findDietByName(Constants.DIET_NAME_OTHER);
					d = inDiet;
					System.out.println("diet-other:" + d.getName() + "\n");
				}
				System.out.println("diet-exists:" + d.getName() + "\n");

				if (row.getCell(19) != null) {

					String str = String.valueOf(row.getCell(16).getRawValue());

					a = new Analysis();
					a.setAnalysisStatus((int) row.getCell(19).getNumericCellValue());
					a.setStartedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(20)));
					a.setCompletedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(21)));
					a.setReleasedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(22)));
					a.setViralLoad(str);
					a.setViralLoadLog(ProcessType.getCellDoubleValue(row.getCell(17)));
					a.setReasonother(ProcessType.getCellStringValue(row.getCell(34)));
					// a.setPatient(p);
					// a.setTest(t);
					// a.setVlReason(vr);
					// a.setVihType(vt);

					/// a = analysisRepository.save(a);

					System.out.println("analysis-status:" + a.getAnalysisStatus() + "\n");
					System.out.println("analysis-started-date:" + a.getStartedDate() + "\n");
					System.out.println("analysis-completed-date:" + a.getCompletedDate() + "\n");
					System.out.println("analysis-released-date:" + a.getReleasedDate() + "\n");

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