package org.itechciv.dashboard.iservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.itechciv.dashboard.helper.CategoryAge;
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


List<Object[]> tendancetest2021();
List<Object[]> tendancetest2022();
List<Object[]> tendancetest2023();

// test par tranche d age region
List<Object[]> yourMethodName(Long regionId, int year);
List<Object[]> yourMethodNameOne(Long regionId, int year);
List<Object[]> yourMethodNametwo(Long regionId, int year);
List<Object[]> yourMethodNamethree(Long regionId, int year);
List<Object[]> yourMethodNamefour(Long regionId, int year);
List<Object[]> yourMethodNameFive(Long regionId, int year);
List<Object[]> yourMethodNameSix(Long regionId, int year);
List<Object[]> yourMethodNameseven(Long regionId, int year);
List<Object[]> yourMethodNameheight(Long regionId, int year);
List<Object[]> yourMethodNamenine(Long regionId, int year);
List<Object[]> yourMethodNameten(Long regionId, int year);
List<Object[]> yourMethodNameeleven(Long regionId, int year);


// test par tranche d age district
List<Object[]> yourMethodDistrictName(Long districtId, int year);
List<Object[]> yourMethodDistrictNameOne(Long districtId, int year);
List<Object[]> yourMethodDistrictNametwo(Long districtId, int year);
List<Object[]> yourMethodDistrictNamethree(Long districtId, int year);
List<Object[]> yourMethodDistrictNamefour(Long districtId, int year);
List<Object[]> yourMethodDistrictNameFive(Long districtId, int year); 
List<Object[]> yourMethodDistrictNameSix(Long districtId, int year);
List<Object[]> yourMethodDistrictNameseven(Long districtId, int year);
List<Object[]> yourMethodDistrictNameheight(Long districtId, int year);
List<Object[]> yourMethodDistrictNamenine(Long districtId, int year);
List<Object[]> yourMethodDistrictNameten(Long districtId, int year);
List<Object[]> yourMethodDistrictNameeleven(Long districtId, int year);

// test par tranche d age site
List<Object[]> yourMethodSiteName(Long siteId, int year);
List<Object[]> yourMethodSiteNameOne(Long siteId, int year);
List<Object[]> yourMethodSiteNametwo(Long siteId, int year);
List<Object[]> yourMethodSiteNamethree(Long siteId, int year);
List<Object[]> yourMethodSiteNamefour(Long siteId, int year);
List<Object[]> yourMethodSiteNameFive(Long siteId, int year);
List<Object[]> yourMethodSiteNameSix(Long siteId, int year);
List<Object[]> yourMethodSiteNameseven(Long siteId, int year);
List<Object[]> yourMethodSiteNameheight(Long districtId, int year);
List<Object[]> yourMethodSiteNamenine(Long siteId, int year);
List<Object[]> yourMethodSiteNameten(Long siteId, int year);
List<Object[]> yourMethodSiteNameeleven(Long siteId, int year);

//pour toute les regions

List<Object[]>MethodRegion(int year);
List<Object[]>MethodRegionOne(int year);
List<Object[]>MethodRegionThree(int year);
List<Object[]>MethodRegionfour(int year);
List<Object[]>MethodRegionfive(int year);
List<Object[]>MethodRegionsix(int year);
List<Object[]>MethodRegionseven(int year);
List<Object[]>MethodRegionheight(int year);
List<Object[]>MethodRegionnine(int year);
List<Object[]>MethodRegionten(int year);
List<Object[]>MethodRegioneleven(int year);
List<Object[]>MethodRegiontwelve(int year);
List<Object[]> findByYearByRegiontest(int year, Long regionId);


List<Object[]> getAnalysisPatientMaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> getAnalysisPatientFemaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> getAnalysisPatientMaleForRegionWithYearM(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getAnalysisPatientFemaleForRegionWithYearF(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getAnalysisPatientMaleForSiteWithYearSiteM(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> getAnalysisPatientFemaleForSiteWithYearsiteF(@RequestParam Long siteId, @RequestParam int year);


List<Object[]> getPatientForAllRegion(@Param("year") int year);
List<Object[]> getPatientForDistrict(@Param("districtId") Long districtId , @Param("year") int year);
List<Object[]> getPatientForDistrictWithSite(@Param("districtId") Long districtId , @Param("year") int year);

List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(Long districtId, int year);
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneSite(@Param("siteId") Long siteId, @Param("year") int year);

List<Object[]> getTestBySiteForOneRegion(@Param("regionId") Long regionId , @Param("year") int year);
List<Object[]> getPatientBySiteForOneRegion(@Param("regionId") Long regionId , @Param("year") int year);

List<Object[]> getTestByPartner(@Param("year") int year);
List<Object[]> getPatientByPartner(@Param("year") int year);

List<Object[]> getestByspecimenEDTAPlasma(@Param("partnerId") int partnerId);
List<Object[]> getestByspecimenDBS(@Param("partnerId") int partnerId);
List<Object[]> getestByspecimenPSC(@Param("partnerId") int partnerId);

List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> district7(@Param("districtId") Long districtId, @Param("year") int year);

List<Object[]> site8(@Param("siteId") Long siteId, @Param("year") int year);

List<Object[]> getTestWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]> getPatientWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);


List<Object[]> districtSeven(@Param("districtId") Long districtId, @Param("year") int year);
List<Object[]>districtEleven(@Param("districtId") Long districtId , @Param("year") int year);

List<Object[]>testOne(@Param("year") int year);
List<Object[]>testTwo(@Param("year") int year);
List<Object[]>testThree(@Param("year") int year);
List<Object[]>testFour(@Param("year") int year);
List<Object[]>testFive(@Param("year") int year);
List<Object[]>testSix(@Param("year") int year); 

List<Object[]> patientOne(@Param("year") int year);
List<Object[]>patientTwo(@Param("year") int year);
List<Object[]>patientThree(@Param("year") int year);
List<Object[]>patientFour(@Param("year") int year);
List<Object[]>patientFive(@Param("year") int year);
List<Object[]>patientSix(@Param("year") int year);

List<Object[]> getTestForSpecificPartner(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]> getPatientForSpecificPartner(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]>getPatientForOnePartnerByMale(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]>getPatientForOnePartnerByFemale(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestForOnePartnerByMale(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestForOnePartnerByFemale(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> motifVlreasonByOnePartner(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestBySiteForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);

List<Object[]> getPatientBySiteForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);


List<Object[]> geTestByCategoryCDCForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);
List<Object[]> geTestByCategoryNationalForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);
List<Object[]> getPatientByCategoryCdciForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);
List<Object[]> getPatientByCategoryNationalForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);





/************************************************************************************************************************ */
//List<Object[]>getTestByCategoryAgeMinus2(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestByCategoryAgeBetweenTwoAndNine(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenTenAndFourteen(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeGreaterThanTwentyFive(@Param("year") int year, @Param("partnerId") Long partnerId);


//List<Object[]> testCategorieOptimize(@Param("year") int year, @Param("partnerId") Long partnerId);
















































    



























}
