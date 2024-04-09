package org.itechciv.dashboard.impservice;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.model.Patient;
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
import org.apache.poi.ss.usermodel.FormulaEvaluator;

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
	public boolean storeExcelImport(MultipartFile file) {

		Test t = null;
		Facilitys f = null;
		Patient p = null;

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
							System.out.println("Test:" +t.toString());
						}						
				}
				// Facilitys
				if (row.getCell(7) != null) {
					String str = String.valueOf(row.getCell(7).getRawValue());

					f = facilitysRepository.findFacilitysByOldCode(str);
					
					if(f==null) {
						Facilitys inFacilitys = new Facilitys();
               		try {
						inFacilitys.setOldCodeFacilitysDHIS2(str);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
               		inFacilitys.setNameSite(row.getCell(8).getStringCellValue());
					inFacilitys.setCodeSiteDatim(ProcessString.getCellStringValue(row.getCell(9)));
					inFacilitys.setNameSiteDatim(ProcessString.getCellStringValue(row.getCell(10)));
					 
					f = facilitysRepository.save(inFacilitys);
					 
					//System.out.println("inserted-facilitys:" +f.getId()+"\n");

					}else {
						f.setNameSite(row.getCell(8).getStringCellValue());
						f.setCodeSiteDatim(ProcessString.getCellStringValue(row.getCell(9)));
						f.setNameSiteDatim(ProcessString.getCellStringValue(row.getCell(10)));
						
						f = facilitysRepository.saveAndFlush(f);
						
						//System.out.println("updated-facilitys:" +f.getId()+"\n");
					} 	
				}      
				//Patient
				  if(row.getCell(4) != null) {
					 p = patientRepository.findPatientByCode(row.getCell(4).getRawValue());
					 
					 if(p==null) {
						 Patient inPatient = new Patient();
						        
								/*String str = String.valueOf(row.getCell(2).getRawValue());
								inPatient.setSubjectno(str);*/
								inPatient.setSubjectno(ProcessString.getCellStringValue(row.getCell(2)));
								inPatient.setSubjectid(ProcessString.getCellStringValue(row.getCell(4)));
								inPatient.setGender(row.getCell(11).getStringCellValue());
								inPatient.setBirthDate(ProcessDate.getCellDateValue(evaluator,row.getCell(12)));
								inPatient.setAgeYears((int) row.getCell(13).getNumericCellValue());
								inPatient.setAgeMonths((int) row.getCell(14).getNumericCellValue());
								inPatient.setAgeWeeks((int) row.getCell(15).getNumericCellValue());
								inPatient.setArvInitDate(ProcessDate.getCellDateValue(evaluator,row.getCell(26)));
								inPatient.setFacilitys(f);
			            			
		                		 p = patientRepository.save(inPatient);
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

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 


			//int cpt = 0;
			for (int i = 7; i < spreadsheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = spreadsheet.getRow(i);

				if (row.getCell(12) != null) {

					if (row.getCell(12).getCellType() == CellType.STRING) {

						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String dateInString = row.getCell(12).getStringCellValue();

						try {
							Date date = formatter.parse(dateInString);
							System.out.println("simple-date: " + date + "\n");
							System.out.println("formated-date: " + formatter.format(date));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				if (row.getCell(5).getCellType() == CellType.STRING) {
					String dateInString = row.getCell(5).getStringCellValue();
					DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.FRENCH);

					System.out.println("Value-localdatetime: " + row.getCell(5).getStringCellValue() + "\n");

				}

				if (row.getCell(4) != null) {

					if (row.getCell(4).getCellType() == CellType.STRING) {
						System.out.println("subject-id-string: " + row.getCell(4).getStringCellValue() + "\n");
					} else if (row.getCell(4).getCellType() == CellType.NUMERIC) {
						System.out.println("subject-id-numeric: " + row.getCell(4).getNumericCellValue() + "\n");
					}
				}

				if (row.getCell(9) != null) {

					if (row.getCell(9).getCellType() == CellType.STRING) {
						System.out.println("code-site-datim-string: " + row.getCell(9).getStringCellValue() + "\n");
					} else if (row.getCell(9).getCellType() == CellType.NUMERIC) {
						System.out.println("code-site-datim-numeric: " + row.getCell(9).getNumericCellValue() + "\n");
					}
				}

				if (row.getCell(10) != null) {

					if (row.getCell(10).getCellType() == CellType.STRING) {
						System.out.println("name-site-datim-string: " + row.getCell(10).getStringCellValue() + "\n");
					} else if (row.getCell(10).getCellType() == CellType.NUMERIC) {
						System.out.println("name-site-datim-numeric: " + row.getCell(9).getNumericCellValue() + "\n");
					}
				}

				if (row.getCell(2) != null) {

					if (row.getCell(2).getCellType() == CellType.STRING) {
						System.out.println("subjectno-string: " + row.getCell(2).getStringCellValue() + "\n");
					} else if (row.getCell(2).getCellType() == CellType.NUMERIC) {
						System.out.println("subjectno-numeric: " + row.getCell(2).getNumericCellValue() + "\n");
					}
				} else {
					System.out.println("Colonne inexistante");
				}
				
				if (row.getCell(9) != null) {

					if (row.getCell(9).getCellType() == CellType.STRING) {
						System.out.println("subjectno-string: " + row.getCell(9).getStringCellValue() + "\n");
					} else if (row.getCell(9).getCellType() == CellType.NUMERIC) {
						System.out.println("subjectno-numeric: " + row.getCell(9).getNumericCellValue() + "\n");
					}
				} 
				
				
				
				if (row.getCell(26) != null) {

					if (row.getCell(26).getCellType() == CellType.STRING) {
						System.out.println("arv-init-date-string: " + row.getCell(26).getStringCellValue() + "\n");
					} else if (row.getCell(26).getCellType() == CellType.NUMERIC) {
						System.out.println("arv-init-date-int: " + row.getCell(26).getNumericCellValue() + "\n");
					}
				} 
				
				if(ObjectUtils.isEmpty(row.getRowNum()== 2)){
					continue;
				}
				
				if (row.getCell(4) != null) {
					try {
						p = patientRepository.findPatientByCode(row.getCell(4).getStringCellValue());

						if (p == null) {
							Patient inPatient = new Patient();
							try {
								if(ObjectUtils.isEmpty(row.getRowNum()== 2)){
									continue;
								}else {
									String str = String.valueOf(row.getCell(2).getRawValue());
									inPatient.setSubjectno(str);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							try {
								if (row.getCell(4).getStringCellValue() == null) {
									inPatient.setSubjectid("");
								} else {
									inPatient.setSubjectid(row.getCell(4).getStringCellValue());
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							inPatient.setGender(row.getCell(11).getStringCellValue());

							try {
								inPatient.setBirthDate(ProcessDate.getCellDateValue(evaluator, row.getCell(12)));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							inPatient.setAgeYears((int) row.getCell(13).getNumericCellValue());
							inPatient.setAgeMonths((int) row.getCell(14).getNumericCellValue());
							inPatient.setAgeWeeks((int) row.getCell(15).getNumericCellValue());
							
							 try { 
								 inPatient.setArvInitDate(ProcessDate.getCellDateValue(evaluator, row.getCell(26)));
							 }catch(Exception ex) { 
								 ex.printStackTrace(); 
							    }
							System.out.println("Patient: " + inPatient.toString() + "\n");

							
							//p = patientRepository.save(inPatient);
							/*
							 * if (p != null) { System.out.println("Patient-saved:" + p.toString() + "\n");
							 * } else { System.out.println("Patient inexistant"); } break;
							 */
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						break;
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