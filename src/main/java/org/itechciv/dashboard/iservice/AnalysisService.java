package org.itechciv.dashboard.iservice;

import java.time.LocalDateTime;
import java.util.List;

import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.response.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AnalysisService extends GenericService<Analysis, Long> {

    List<Object[]> getAnalysisByspecimenEDTAData(int regionName);
    List<Object[]> getAnalysisByspecimenDBSData(int regionName);
    List<Object[]> getAnalysisByspecimenPSCData(int regionName);
    List<Object[]> getAnalysisBySpecimenDISTRICTEDTA(int districtName);
    List<Object[]> getAnalysisBySpecimenDISTRICTDBS(int districtName);
    List<Object[]> getAnalysisBySpecimenDISTRICTPSC(int districtName);
    List<Object[]> getAnalysisBySpecimenSITEEDTA(int siteName);
    List<Object[]> getAnalysisBySpecimenSITEDBS(int siteName);
    List<Object[]> getAnalysisBySpecimenSITEPSC(int siteName);
    List<Object[]> getAnalysisByspecimenGeneralEDTAData();
    List<Object[]> getAnalysisByspecimenGeneralDBSData();
    List<Object[]> getAnalysisByspecimenGeneralPSCData();
    List<Object[]> getAnalysisByYear(int year, Long regionId);
    List<Object[]> findByYearReg(int year);
    List<Object[]> findByYearDistrict(int year, Long districtId);
    List<Object[]> findByYearSite(int year, Long siteId);
    List<Object[]> findByYearByPatient(int year, Long regionId);
    List<Object[]> findByYearByPatientByDistrict(int year, Long districtId);
    List<Object[]> findByYearByPatientBySite(int year, Long siteId);
    List<Object[]> findByYearByPatientByTouteRegion(int year);
    List<Object[]> motifVlreasonByAllRegions(int year);
    List<Object[]> motifVlreasonByOneRegion(int year, Long regionId);
    List<Object[]> motifVlreasonByDistrict(int year, Long districtId);
    List<Object[]> motifVlreasonBySite(int year, Long siteId);
    long getTotalTestsByYear(int year);
    List<Object[]> listeTestByRegion(int year);
    List<Object[]> listeTestByDistrict(int year);
    List<Object[]> listeTestBySite(int year);
    List<Object[]> listeTestBySiteInDistrict(int year, Long districtId);
    List<Object[]> getAnalysisMaleForRegionWithSpecificYearM(Long regionId, int year);
    List<Object[]> getAnalysisFemaleForRegionWithSpecificYear(Long regionId, int year);
    List<Object[]> getAnalysisMaleForDistrictWithYear(Long districtId, int year);
    List<Object[]> getAnalysisFemaleForDistrictWithYear(Long districtId, int year);
    List<Object[]> getAnalysisMaleForSiteWithYear(Long siteId, int year);
    List<Object[]> getAnalysisFemaleForSiteWithYear(Long siteId, int year);
    List<Object[]> getAnalysisMaleByRegionWithYear(int year);
    List<Object[]> getAnalysisFemaleByRegionWithYear(int year);
    List<Object[]> getAnalysisPatientMaleByRegionWithSpecificYear(int year);
    List<Object[]> getAnalysisPatientFemaleByRegionWithSpecificYear(int year);
    //List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(Long regionId, int year);
    //List<Object[]> test(Long regionId, int year);

List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeMinusTwoForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(@Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year); 
List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryMinusTwoForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@Param("year") int year);
List<Object[]> getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(@Param("year") int year);

List<Object[]> getTestByAgeMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeBetweenTwoAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeGreaterThanTwentyFiveForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeMinusTwoForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeBetweenTwoAndNineForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeBetweenTenAndFourteenForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeGreaterThanTwentyFiveForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryGreaterThanFiftyForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryMinusTwoForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenOneAndFourForAllegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryGreaterThanFiftyForAllRegion(@Param("year") int year);



















    



























}
