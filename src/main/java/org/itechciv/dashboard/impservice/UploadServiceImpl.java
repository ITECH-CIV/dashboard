package org.itechciv.dashboard.impservice;


import java.util.List;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.itechciv.dashboard.helper.CategoryAge;
import org.itechciv.dashboard.helper.Constants;
import org.itechciv.dashboard.helper.ProcessString;
import org.itechciv.dashboard.helper.ProcessType;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.model.Regimen;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.model.Lab;
import org.itechciv.dashboard.model.Partner;
import org.itechciv.dashboard.model.Site;
import org.itechciv.dashboard.model.SitePartner;
import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.model.VlReason;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.repository.RegimenRepository;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.itechciv.dashboard.repository.LabRepository;
import org.itechciv.dashboard.repository.PartnerRepository;
import org.itechciv.dashboard.repository.SiteRepository;
import org.itechciv.dashboard.repository.PatientRepository;
import org.itechciv.dashboard.repository.RegionRepository;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.itechciv.dashboard.repository.SitePartnerRepository;
import org.itechciv.dashboard.repository.TestRepository;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.itechciv.dashboard.repository.VlReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.util.ArrayList;
import java.util.Arrays;

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

	@Autowired
	private VihTypeRepository vihTypeRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private SampleTypeRepository sampleTypeRepository;

	@Autowired
	private SitePartnerRepository sitePartnerRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private RegimenRepository regimenRepository;

	@Autowired
	private VlReasonRepository vlReasonRepository;

	@Autowired
	private AnalysisRepository analysisRepository;

	@PersistenceContext
	private EntityManager em;

	List<CategoryAge> cdcAgeCategories;
	List<CategoryAge> nationalAgeCategories;

	// Import Lab Data
	@Override
	public boolean storeLabImport(MultipartFile file) {

		Lab l = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

			XSSFSheet spreadsheet = workbook.getSheetAt(0);

			System.out.println("LENGTH ::::: " + spreadsheet.getPhysicalNumberOfRows() + "\n");

			for (int i = 2; i < spreadsheet.getPhysicalNumberOfRows() + 1; i++) {

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
		Partner p = null;

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
			return false;
		}

	}

	@Override
	public boolean storeExcelImport(MultipartFile file) {
		Test t = null;
		Site s = null;
		VihType vt = null;
		VihType inVht = null;
		Lab lab = null;
		Lab inLab = null;
		Patient p = null;
		Regimen reg = null;
		Regimen inReg = null;
		SampleType st = null;
		VlReason vr = null;
		Analysis a = null;

		Integer ageCdcId = null;
		Integer ageNationalId = null;

		cdcAgeCategories = this.getCDCAgeCategory();
		nationalAgeCategories = this.getNationalAgeCategory();

		try {

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

					// VihType
					if (row.getCell(23) != null) {

						vt = vihTypeRepository.findVihTypeByName(ProcessType.getCellStringValue(row.getCell(23)));

						boolean checkVihType = (vt == null);

						if (checkVihType) {

							inVht = vihTypeRepository.findVihTypeByName(Constants.VIH_TYPE_NAME_OTHER);

							vt = inVht;
						}
					}

					p = new Patient();

					p.setSubjectno(ProcessType.getCellStringValue(row.getCell(2)));
					p.setSubjectid(ProcessType.getCellStringValue(row.getCell(4)));
					p.setGender(row.getCell(11).getStringCellValue());
					p.setBirthDate(ProcessType.getCellDateValue(evaluator, row.getCell(12)));
					p.setAgeYears((int) row.getCell(13).getNumericCellValue());
					p.setAgeMonths((int) row.getCell(14).getNumericCellValue());
					p.setAgeWeeks((int) row.getCell(15).getNumericCellValue());
					p.setArvInitDate(ProcessType.getCellDateValue(evaluator, row.getCell(26)));
					p.setSite(s);
					p.setVihType(vt);

					p = patientRepository.save(p);
				}
				// Analyse

				if (row.getCell(19) != null) {

					// Regimen
					String molecule = ProcessString.concatenateCurrentValue(row.getCell(28), row.getCell(29), row.getCell(30));
					// System.out.println("concatenateCurrentValue ::: " + molecule + "\n");
					reg = regimenRepository.findRegimenByName(molecule);
					// System.out.println("diet-values:" + d.getName() + "\n" );
					boolean checkRegimen = (reg == null);

					if (checkRegimen) {
						inReg = regimenRepository.findRegimenByName(Constants.REGIMEN_NAME_OTHER);
						reg = inReg;
						// System.out.println("regimen-other:" + reg.getName() + "\n");
					}

					// Sample type
					if (row.getCell(18) != null) {

						st = sampleTypeRepository.findSampleTypeByName(ProcessType.getCellStringValue(row.getCell(18)));

						if (st == null) {
							SampleType inSampleType = new SampleType();
							inSampleType.setLabel(ProcessType.getCellStringValue(row.getCell(18)));

							st = sampleTypeRepository.save(inSampleType);
						}
					}

					// VlReason

					vr = vlReasonRepository.findVlReasonByName(ProcessType.getCellStringValue(row.getCell(33)));

					if (vr == null) {
						VlReason inVlReason = new VlReason();
						inVlReason.setName(ProcessType.getCellStringValue(row.getCell(33)));

						vr = vlReasonRepository.save(inVlReason);
					}

					// Lab

					if (row.getCell(0) != null) {

						String labValue = ProcessString.labNoSubValue(row.getCell(0));

						// System.out.println("lab-value:" + labValue + "\n");

						lab = labRepository.findLabByPrefix(labValue);

						// System.out.println("Lab-existing:" + lab + "\n");

						boolean checkLabValue = (lab == null);

						if (checkLabValue) {
							inLab = labRepository.findLabByPrefix(Constants.LAB_NAME_OTHER);
							// System.out.println("Lab-constant:" + inLab.getPrefix() + "\n");

							lab = inLab;
						}
					}
					// Analysis

					a = new Analysis();

					if (row.getCell(16).getCellType() == CellType.STRING) {

						String str = row.getCell(16).getStringCellValue();

						// System.out.println("viral-load-string: " +
						// row.getCell(16).getStringCellValue() + "\n");

						List<String> tabConstants = Arrays.asList("<LL", "< LL", "LL");

						// a = new Analysis();

						if (tabConstants.contains(str)) {
							a.setGrossResult(str);
							a.setConvertedResult(0);

							// System.out.println("valeur-49: " + a.getGrossResult() + " " +
							// a.getConvertedResult() + "\n");

						} else {
							a.setGrossResult("");
							a.setConvertedResult(-1);

							// System.out.println("valeur-XXXX: " + a.getGrossResult() + " " +
							// a.getConvertedResult() + "\n");

						}
					}

					if (row.getCell(16).getCellType() == CellType.NUMERIC) {

						// System.out.println("viral-load-numeric: " +
						// row.getCell(16).getNumericCellValue() + "\n");

						a.setGrossResult("");
						a.setConvertedResult((int) row.getCell(16).getNumericCellValue());

						// System.out.println("valeur-numeric: " + a.getGrossResult() + " " +
						// a.getConvertedResult() + "\n");

					}

					ageCdcId = getCDCAgeCategorieId((int) row.getCell(13).getNumericCellValue());
					ageNationalId = getNationalCategoriesId((int) row.getCell(13).getNumericCellValue());

					System.out.println("row.getCell(13).getNumericCellValue(): "+row.getCell(13).getNumericCellValue());

					System.out.println("listeCdci:" +this.getCDCAgeCategory().get(0).toString());
					System.out.println("listeNational:" +this.getNationalAgeCategory().get(0).toString());
					System.out.println("ageCdcId:"+ageCdcId);
					System.out.println("ageNationalId:"+ageNationalId);

					a.setAgeCdc(ageCdcId);
					a.setAgeNational(ageNationalId);
					a.setAnalysisStatus((int) row.getCell(19).getNumericCellValue());
					a.setCompletedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(21)));
					a.setReleasedDate(ProcessType.toLocalDateTime(evaluator, row.getCell(22)));
					a.setLabno(ProcessType.getCellStringValue(row.getCell(0)));
					a.setDintv(ProcessType.toLocalDateTime(evaluator, row.getCell(6)));
					a.setDrcpt(ProcessType.toLocalDateTime(evaluator, row.getCell(5)));
					a.setSampleType(st);
					a.setTest(t);
					a.setPatient(p);
					a.setRegimen(reg);
					a.setVlReason(vr);
					a.setLab(lab);

					a = analysisRepository.save(a);

				}

				workbook.close();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}

	// Mise à jour des sites avec nameSite, codeSiteDatim, nameSiteDatim
	@Override
	public boolean updateSite(MultipartFile file) {

		Site s = null;
		Test t = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);

			for (int i = 1; i < spreadsheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = spreadsheet.getRow(i);
				// Test
				if (row.getCell(3) != null) {

					t = testRepository.findTestByName(row.getCell(3).getStringCellValue());
					// System.out.println("Test:" + t + "\n");

					if (t == null) {
						Test inTest = new Test();
						inTest.setName(Constants.TEST_NAME);
						inTest.setStudy(row.getCell(3).getStringCellValue());

						t = testRepository.save(inTest);
						// System.out.println("Test:" + t.toString());
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
				workbook.close();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean storePartnerImport(MultipartFile file) {

		Site s = null;
		Partner p = null;
		SitePartner sp = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(1);
			System.out.println("SHEET-NAME: " + spreadsheet.getSheetName().toString() + "\n");

			for (int i = 6; i < spreadsheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = spreadsheet.getRow(i);

				System.out.println("column-name: " + row.getCell(0).getStringCellValue() + "\n");
				System.out.println("column-name---1: " + row.getCell(1).getStringCellValue() + "\n");
				System.out.println("column-name---6: " + row.getCell(6).getStringCellValue() + "\n");

				if (row.getCell(1) != null) {

					s = siteRepository.findSiteByCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(1)));
					// List<Site> sites =
					// siteRepository.findByCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(1)));
					// s= sites.get(0);

					if (s == null) {
						Site inSite = new Site();
						inSite.setNameSite(ProcessType.getCellStringValue(row.getCell(0)));
						inSite.setCodeSiteDatim(ProcessType.getCellStringValue(row.getCell(1)));

						System.out.println("site-name:" + inSite.getNameSite() + "\n");
						System.out.println("site-code-datim:" + inSite.getCodeSiteDatim() + "\n");

						s = siteRepository.save(inSite);
					}
				}

				if (row.getCell(6) != null) {

					p = partnerRepository.findPartnerByName(ProcessType.getCellStringValue(row.getCell(6)));
					System.out.println("partner-get:" + ProcessType.getCellStringValue(row.getCell(6)) + "\n");

					if (p == null) {
						Partner inPartner = new Partner();
						inPartner.setName(ProcessType.getCellStringValue(row.getCell(6)));

						System.out.println("partner-name:" + inPartner.getName() + "\n");

						p = partnerRepository.save(inPartner);
					}
				}

				sp = new SitePartner();
				sp.setSite(s);
				sp.setPartner(p);

				sp = sitePartnerRepository.save(sp);

				workbook.close();
			}

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean uploadTestImport(MultipartFile file) {

		Lab lab = null;
		Lab inLab = null;

		Site s = null;
		Partner p = null;
		SitePartner sp = null;
		Analysis a = null;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
			System.out.println("SHEET-NAME: " + spreadsheet.getSheetName().toString() + "\n");

			for (int i = 1; i < spreadsheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = spreadsheet.getRow(i);

				if (row.getCell(19) != null) {

					if (row.getCell(16).getCellType() == CellType.STRING) {

						String str = row.getCell(16).getStringCellValue();

						System.out.println("viral-load-string: " + row.getCell(16).getStringCellValue() + "\n");

						List<String> tabConstants = Arrays.asList("<LL", "< LL", "LL");

						if (tabConstants.contains(str)) {

							a = new Analysis();
							a.setGrossResult(str);
							a.setConvertedResult(0);

							System.out
									.println("valeur-49: " + a.getGrossResult() + " " + a.getConvertedResult() + "\n");

						} else {
							a = new Analysis();
							a.setGrossResult("");
							a.setConvertedResult(-1);

							System.out.println(
									"valeur-XXXX: " + a.getGrossResult() + " " + a.getConvertedResult() + "\n");

						}
					}

					if (row.getCell(16).getCellType() == CellType.NUMERIC) {

						System.out.println("viral-load-numeric: " + row.getCell(16).getNumericCellValue() + "\n");

						a = new Analysis();
						a.setGrossResult("");
						a.setConvertedResult((int) row.getCell(16).getNumericCellValue());

						System.out.println("valeur-numeric: " + a.getGrossResult() + " " + a.getConvertedResult() + "\n");

					}
					// a = analysisRepository.save(a);

				}

				// Lab
				if (row.getCell(0) != null) {

					String labValue = ProcessString.labNoSubValue(row.getCell(0));

					System.out.println("lab-value:" + labValue + "\n");

					lab = labRepository.findLabByPrefix(labValue);

					System.out.println("Lab-existing:" + lab + "\n");

					boolean checkLabValue = (lab == null);

					if (checkLabValue) {
						inLab = labRepository.findLabByPrefix(Constants.LAB_NAME_OTHER);
						// System.out.println("Lab-constant:" + inLab.getPrefix() + "\n");

						lab = inLab;
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

	public List<CategoryAge> getCDCAgeCategory() {
		String sql = "SELECT id, min_cat, max_cat FROM dashboard.age_category where type = 'CDC CI'";
		List<CategoryAge> response = new ArrayList<CategoryAge>();
		try {
			Query query = em.createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				CategoryAge ageClass = new CategoryAge();
				ageClass.setId(Integer.parseInt(o[0].toString()));
				ageClass.setAgeMin(Integer.parseInt(o[1].toString()));
				ageClass.setAgeMax(Integer.parseInt(o[2].toString()));

				response.add(ageClass);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public List<CategoryAge> getNationalAgeCategory() {
		String sql = "SELECT id, min_cat, max_cat FROM dashboard.age_category where type = 'National'";
		List<CategoryAge> response = new ArrayList<CategoryAge>();
		try {
			Query query = em.createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			for (Object[] o : results) {
				CategoryAge ageClass = new CategoryAge();
				ageClass.setId(Integer.parseInt(o[0].toString()));
				ageClass.setAgeMin(Integer.parseInt(o[1].toString()));
				ageClass.setAgeMax(Integer.parseInt(o[2].toString()));

				response.add(ageClass);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Integer getCDCAgeCategorieId(Integer age) {
		Integer cat = null;
		for (CategoryAge ageCat : cdcAgeCategories) {
			if (age >= ageCat.getAgeMin() && age < ageCat.getAgeMax()) {
				cat = ageCat.getId();
				break;
			}
		}
		return cat;
	}

	public Integer getNationalCategoriesId(Integer age) {
		Integer cat = null;
		for (CategoryAge ageCat : nationalAgeCategories) {
			if (age >= ageCat.getAgeMin() && age < ageCat.getAgeMax()) {
				cat = ageCat.getId();
				break;
			}
		}
		return cat;
	}

}