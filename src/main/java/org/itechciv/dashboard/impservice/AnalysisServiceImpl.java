package org.itechciv.dashboard.impservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.itechciv.dashboard.iservice.AnalysisService;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnalysisServiceImpl extends GenericServiceImpl<Analysis, Long> implements AnalysisService {

    @Autowired
	private AnalysisRepository analisysRepo;

    //service pour le premier graph par specimen EDTA
    @Override
    public List<Object[]> getAnalysisByspecimenEDTAData(int regionName) {
        try{
            return analisysRepo.getAnalysisByspecimenEDTAData(regionName);
        }catch(Exception ex){
         ex.printStackTrace();
		return Collections.emptyList();
        }
    }

    //service pour le premier graph par specimen DBS
    @Override
    public List<Object[]> getAnalysisByspecimenDBSData(int regionName) {
        try{
            return analisysRepo.getAnalysisByspecimenDBSData(regionName);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
    }

    //service pour le premier graph par specimen PSC
    @Override
    public List<Object[]> getAnalysisByspecimenPSCData(int regionName) {
        try{
            return analisysRepo.getAnalysisByspecimenPSCData(regionName);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
    }

    //DISTRICT DBS , EDTA , PSC
    @Override
    public List<Object[]> getAnalysisBySpecimenDISTRICTPSC(int districtName) {
        try{
            return analisysRepo.getAnalysisBySpecimenDISTRICTPSC(districtName);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisBySpecimenDISTRICTDBS(int districtName) {
        try{
            return analisysRepo.getAnalysisBySpecimenDISTRICTDBS(districtName);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisBySpecimenDISTRICTEDTA(int districtName) {
        try{
            return analisysRepo.getAnalysisBySpecimenDISTRICTEDTA(districtName);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    //SITE DBS, EDTA , PSC
    @Override
    public List<Object[]> getAnalysisBySpecimenSITEPSC(int siteName) {
        try{
            return analisysRepo.getAnalysisBySpecimenSITEPSC(siteName);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisBySpecimenSITEDBS(int siteName) {
        try{
            return analisysRepo.getAnalysisBySpecimenSITEDBS(siteName);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisBySpecimenSITEEDTA(int siteName) {
        try{
            return analisysRepo.getAnalysisBySpecimenSITEEDTA(siteName);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    //SERVICE POUR TOUTE LES REGIONS
    @Override
    public List<Object[]> getAnalysisByspecimenGeneralEDTAData() {
        try{
            return analisysRepo.getAnalysisByspecimenGeneralEDTAData();
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

	@Override
    public List<Object[]> getAnalysisByspecimenGeneralDBSData() {
        try{
            return analisysRepo.getAnalysisByspecimenGeneralDBSData();
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisByspecimenGeneralPSCData() {
        try{
            return analisysRepo.getAnalysisByspecimenGeneralPSCData();
        }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
    }
    }

    //service charge virale par test pour chaque  region
    @Override
    public List<Object[]> getAnalysisByYear(int year, Long regionId) {
        try{
            return analisysRepo.findByYear(year, regionId);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    //service charge virale par test <LL, >1000 etc.. pour toute les regions
    @Override
    public List<Object[]> findByYearReg(int year) {
        try{
            return analisysRepo.findByYearReg(year);
        } catch(Exception ex){
          ex.printStackTrace();
          return Collections.emptyList();
        }
    }

    //service charge virale par test pour chaque  district
    @Override
    public List<Object[]> findByYearDistrict(int year, Long districtId) {

        try{
            return analisysRepo.findByYearDistrict(year, districtId);
        }catch(Exception ex){
            ex.printStackTrace();
			return Collections.emptyList();
        }
    }

    //service charge virale par test pour chaque  district
    @Override
    public List<Object[]> findByYearSite(int year, Long siteId) {
        try{
            return analisysRepo.findByYearSite(year, siteId);

        }catch(Exception ex){
           ex.printStackTrace();
           return Collections.emptyList();
        }
    }

    //service charge virale par patientteste  pour chaque  region par patient
@Override
 public List<Object[]> findByYearByPatient(int year, Long regionId) {
    try{
        return analisysRepo.findByYearByPatient(year, regionId);
    }catch(Exception ex){
     ex.printStackTrace();
     return Collections.emptyList();
    }
}

    //patient testé par district
    @Override
    public List<Object[]> findByYearByPatientByDistrict(int year, Long districtId) {

        try{
            return analisysRepo.findByYearByPatientByDistrict(year, districtId);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }

    }

    //patient testé par site
    @Override
    public List<Object[]> findByYearByPatientBySite(int year, Long siteId) {
        try{
            return analisysRepo.findByYearByPatientBySite(year, siteId);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    //pour toute les regions patient Testés
    @Override
    public List<Object[]> findByYearByPatientByTouteRegion(int year) {

        try{
            return analisysRepo.findByYearByPatientByTouteRegion(year);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    // INDICATEUR: MOTIF PAR VLREASON DE chaque REGION
    @Override
    public List<Object[]> motifVlreasonByAllRegions(int year) {
        try{
            return analisysRepo.motifVlreasonByAllRegions(year);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }
    
    // INDICATEUR: MOTIF PAR VLREASON DE chaque REGION
    @Override
    public List<Object[]> motifVlreasonByOneRegion(int year, Long regionId) {
        try{
            return analisysRepo.motifVlreasonByOneRegion(year, regionId);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    // INDICATEUR: MOTIF PAR VLREASON DE chaque DISTRICT
    @Override
    public List<Object[]> motifVlreasonByDistrict(int year, Long districtId) {
        try{
            return analisysRepo.motifVlreasonByDistrict(year, districtId);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    // INDICATEUR: MOTIF PAR VLREASON DE chaque SITE
    @Override
    public List<Object[]> motifVlreasonBySite(int year, Long siteId) {
        try{
            return analisysRepo.motifVlreasonBySite(year, siteId);

        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }

    }

    //service qui calcule le Nombre total de test realises pour mon cardbox
    @Override
    public long getTotalTestsByYear(int year) {
        try{
            return analisysRepo.getTotalTestsByYear(year);
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
    
    //service qui calcule le Nombre total de test realises en fonction de la charge virale pour chaque region
    @Override
    public List<Object[]> listeTestByRegion(int year) {
       try{
        return analisysRepo.listeTestByRegion(year);
       }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList(); 
        }
    }

    @Override
    public List<Object[]> listeTestByDistrict(int year) {
        try{
            return analisysRepo.listeTestByDistrict(year);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    @Override
    public List<Object[]> listeTestBySite(int year) {
        try{
            return analisysRepo.listeTestBySite(year);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    
    // Resultat du site en fonction du district
    @Override
    public List<Object[]> listeTestBySiteInDistrict(int year, Long districtId) {
        try{
            return analisysRepo.listeTestBySiteInDistrict(year, districtId);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    
    //genre masculin
    @Override
    public List<Object[]> getAnalysisMaleForRegionWithSpecificYearM(Long regionId, int year) {
        String sex = "M"; // Sexe masculin
        try{
            return analisysRepo.getAnalysisMaleForRegionWithSpecificYearM(regionId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    @Override
    public List<Object[]> getAnalysisFemaleForRegionWithSpecificYear(Long regionId, int year) {
        String sex = "F"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisFemaleForRegionWithSpecificYear(regionId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    //genre test par district
    @Override
    public List<Object[]> getAnalysisMaleForDistrictWithYear(Long districtId, int year) {
        String sex = "M"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisMaleForDistrictWithYear(districtId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    @Override
    public List<Object[]> getAnalysisFemaleForDistrictWithYear(Long districtId, int year) {
        try{
            String sex = "F"; // Sexe Feminin
            return analisysRepo.getAnalysisFemaleForDistrictWithYear(districtId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    //PAR SITE
    @Override
    public List<Object[]> getAnalysisMaleForSiteWithYear(Long siteId, int year) {
        try{
            String sex = "M"; // Sexe Feminin
            return analisysRepo.getAnalysisMaleForSiteWithYear(siteId, year, sex);  
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();  
        }
    }

    @Override
    public List<Object[]> getAnalysisFemaleForSiteWithYear(Long siteId, int year) {
        String sex = "F"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisFemaleForSiteWithYear(siteId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();  
        }
    }

    
      // POUR TOUTE LES REGIONS  Feminin Masculin
      @Override
      public List<Object[]> getAnalysisMaleByRegionWithYear(int year) {
        String sex = "M"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisMaleByRegionWithYear( year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getAnalysisFemaleByRegionWithYear(int year) {
        String sex = "F"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisFemaleByRegionWithYear( year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

   // INDICATEUR PATIENT TESTES POUR TOUTE LES REGIONS
   @Override
    public List<Object[]> getAnalysisPatientMaleByRegionWithSpecificYear(int year) {
        String sex = "M"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisPatientMaleByRegionWithSpecificYear( year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

	@Override
    public List<Object[]> getAnalysisPatientFemaleByRegionWithSpecificYear(int year) {
        String sex = "F"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisPatientFemaleByRegionWithSpecificYear( year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(Long regionId, int year) {

       try{
        return analisysRepo.getTestedPatientByAgeMinusTwoForOneRegion(regionId, year);
       }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTwoAndNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeMinusTwoForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeMinusTwoForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTwoAndNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(int year) {
        try{
            return analisysRepo.getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryMinusTwoForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryMinusTwoForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryMinusTwoForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }       
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }       
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }      
    }

    @Override
    public List<Object[]> getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(int year) {
        try{
            return analisysRepo.getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }      
    }

    @Override
    public List<Object[]> getTestByAgeMinusTwoForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeMinusTwoForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTwoAndNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTwoAndNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTenAndFourteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTenAndFourteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenFifteenAndNineteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeBetweenFifteenAndNineteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeGreaterThanTwentyFiveForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeGreaterThanTwentyFiveForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeMinusTwoForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeMinusTwoForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTwoAndNineForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTwoAndNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTenAndFourteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTenAndFourteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenFifteenAndNineteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeBetweenFifteenAndNineteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeGreaterThanTwentyFiveForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeGreaterThanTwentyFiveForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryMinusTwoForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryMinusTwoForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenOneAndFourForOneRegion(Long regionId, int year) {
        // TODO Auto-generated method stub
        try{
            return analisysRepo.getTestByAgeCategoryBetweenOneAndFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFiveAndNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryGreaterThanFiftyForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryGreaterThanFiftyForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryMinusTwoForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryMinusTwoForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenOneAndFourForAllegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenOneAndFourForAllegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFiveAndNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

    @Override
    public List<Object[]> getTestByAgeCategoryGreaterThanFiftyForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeCategoryGreaterThanFiftyForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }

 /*********************************************************************************************************************************/

}
