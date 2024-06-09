package org.itechciv.dashboard.iservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.itechciv.dashboard.helper.AgeRange;
import org.itechciv.dashboard.helper.CategoryAge;
import org.itechciv.dashboard.helper.Gender;
import org.itechciv.dashboard.helper.GenderOne;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.response.Response;
import org.springframework.data.jpa.repository.Query;
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
    //long getTotalTestsByYear(int year);
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
//List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year); 
//List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
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
//List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeGreaterThanTwentyFiveForAllRegion(@Param("year") int year);
List<Object[]> getTestByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getTestByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
//List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
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


//List<Object[]> tendancetest2021();
//List<Object[]> tendancetest2022();
//List<Object[]> tendancetest2023();

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


//List<Object[]> getPatientForAllRegion(@Param("year") int year);
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

//List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);
List<Object[]> district7(@Param("districtId") Long districtId, @Param("year") int year);

//List<Object[]> site8(@Param("siteId") Long siteId, @Param("year") int year);

List<Object[]> getTestWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]> getPatientWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);


List<Object[]> districtSeven(@Param("districtId") Long districtId, @Param("year") int year);
//List<Object[]>districtEleven(@Param("districtId") Long districtId , @Param("year") int year);

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


List<Object[]> getTestByRegimenForAllPartner(@Param("year") int year);
List<Object[]> getPatientByRegimenForAllPartner(@Param("year") int year);

List<Object[]> getTestByRegimenForOnePartner(@Param("partnerId") Long partnerId, @Param("year") int year);
List<Object[]> getPatientByRegimenForOnePartner(@Param("partnerId") Long partnerId, @Param("year") int year);

List<Object[]> getestBySpecimenDBSForOneRegimen(@Param("regimenId") Long regimenId);
List<Object[]> getestBySpecimenPSCForOneRegimen(@Param("regimenId") Long regimenId);
List<Object[]> getestBySpecimenEdtaPlasmaForOneRegimen(@Param("regimenId") Long regimenId);

List<Object[]> getTestForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getTestByCDCMaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getTestByCdcFemaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getPatientByCDCMaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getPatientByCDCFemaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getestBySpecimenDBSForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId );
List<Object[]> getestBySpecimenPSCForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId);
List<Object[]> getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId);


List<Object[]> getTestForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year);


List<Object[]> getTestByCDCMaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year);
List<Object[]> getTestByCDCFemaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId,  @Param("partnerId") Long partnerId, @Param("year") int year);

List<Object[]> getPatientByCDCMaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year);
List<Object[]> getPatientByCDCFemaleForRegimenAndParterOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year);


List<Object[]> getTestAndPatientResultForPartnerAndRegion(@Param("year") int year);
List<Object[]> getTestAndPatientResultForOnePartnerAndRegion(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestAndPatientResultForAllLab(@Param("year") int year);
List<Object[]> getTestAndPatientResultForOneLab(@Param("year") int year, @Param("labId") Long labId);
/****************************************************************************************************************************** */
List<Object[]> getestAndPatientBySpecimenDBSForAllLab(@Param("year") int year);
List<Object[]> getestAndPatientBySpecimenPSCForAllLab(@Param("year") int year);
List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForAllLab(@Param("year") int year);
List<Object[]> getestAndPatientBySpecimenDBSForOneLab(@Param("year") int year, @Param("labId") Long labId);
List<Object[]> getestAndPatientBySpecimenPSCForOneLab(@Param("year") int year, @Param("labId") Long labId);
List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForOneLab(@Param("year") int year, @Param("labId") Long labId);
List<Object[]> getTestForAllLab(@Param("year") int year);
List<Object[]> getTestForOneLab(@Param("year") int year, @Param("labId") Long labId);
List<Object[]> getPatientForAllLab(@Param("year") int year);
List<Object[]> getPatientForOneLab(@Param("year") int year, @Param("labId") Long labId);

List<Object[]> getPatientByCDCMaleAndPartnerAll(@Param("year") int year);
List<Object[]> getPatientByCDCFemaleAndPartnerAll(@Param("year") int year);

List<Object[]> getTestByCDCMaleAndPartnerAll(@Param("year") int year);
List<Object[]> getTestByCDCFemaleAndPartnerAll(@Param("year") int year);

List<Object[]> getPatientByCDCMaleAndPartnerOne(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]> getPatientByCDCFemaleAndPartnerOne(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestByCDCMaleAndPartnerOne(@Param("year") int year, @Param("partnerId") Long partnerId);
List<Object[]> getTestByCDCFemaleAndPartnerOne(@Param("year") int year, @Param("partnerId") Long partnerId);

List<Object[]> getTestBySpecimen(@Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getTestByAgeCategoryCdc(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId); 

List<Object[]> getPatientMaleByCdc(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientFemaleByCdc(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientNothingByCdc(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);



/**************************************************DEBUT************************************************* */
//List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
//List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
//List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
//List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
//List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
//List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getPatientForAllRegion(@RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(@RequestParam Long regionId, @RequestParam int year); 
List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(@RequestParam Long regionId, @RequestParam int year);

//tranche d age cidici pour toute les regions patient testé
List<Object[]> getTestedPatientByAgeCategoryMinusTwoForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion( @RequestParam int year);
List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForAllRegion(@RequestParam int year);
List<Object[]> district1(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district2(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district3(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district4(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district5(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district6(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district8(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district9(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district10(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district11(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> district12(@RequestParam Long districtId, @RequestParam int year);






//nombre de patient testés par site detaillé  cdci
List<Object[]> site1(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site2(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site3(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site4(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site5(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site6(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site7(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site8(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site9(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site10(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site11(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> site12(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> districtEight(@RequestParam Long districtId, @RequestParam int year);
 List<Object[]> districtNine(@RequestParam Long districtId, @RequestParam int year);
 List<Object[]> districtTen(@RequestParam Long districtId, @RequestParam int year);
 List<Object[]> districtEleven(@RequestParam Long districtId, @RequestParam int year);
 List<Object[]> districtTwelve(@RequestParam Long districtId, @RequestParam int year);
 List<Object[]> siteSeven(@RequestParam Long siteId, @RequestParam int year);
 List<Object[]> siteEight(@RequestParam Long siteId, @RequestParam int year) ;
 List<Object[]> siteNine(@RequestParam Long siteId, @RequestParam int year);
 List<Object[]> siteTen(@RequestParam Long siteId, @RequestParam int year);
 List<Object[]> siteEleven(@RequestParam Long siteId, @RequestParam int year) ;
 List<Object[]> siteTwelve(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> districtOne(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> districtTwo(@RequestParam Long districtId, @RequestParam int year) ;
List<Object[]> districtThree(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> districtFour(@RequestParam Long districtId, @RequestParam int year) ;
List<Object[]> districtFive(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> districtSix(@RequestParam Long districtId, @RequestParam int year);
List<Object[]> siteOne(@RequestParam Long siteId, @RequestParam int year);
List<Object[]> siteTwo(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> siteThree(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> siteFour(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> siteFive(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> siteSix(@RequestParam Long siteId, @RequestParam int year) ;
List<Object[]> patientOnen( @RequestParam int year);
List<Object[]> patientTwon( @RequestParam int year) ;
List<Object[]> patientThreen( @RequestParam int year) ;
List<Object[]> patientFourn( @RequestParam int year) ;
List<Object[]> patientFiven( @RequestParam int year) ;
List<Object[]> patientSixn( @RequestParam int year) ;
List<Object[]> getestByspecimenEDTAPlasmapartenaire(@RequestParam int partnerId) ;
List<Object[]> getestByspecimenDBSpartenaire(@RequestParam int partnerId) ;
List<Object[]> getestByspecimenPSCpartenaire(@RequestParam int partnerId) ;
List<Object[]> getTestsAndPatientsByRegionliste(@RequestParam int year);
List<Object[]> getTestsAndPatientsByDistrictliste(@RequestParam int year) ;
List<Object[]> getTestsAndPatientsBySitePartnerliste(@RequestParam int year) ;
List<Object[]> getTestsAndPatientsBySitechaquepartenaireliste(@RequestParam int year,@RequestParam Long partnerId);
List<Object[]> getTestByRegimenForAllPartnerone(@RequestParam int year);
List<Object[]> getPatientByRegimenForAllPartnertwo(@RequestParam int year) ;
List<Object[]> getTestByRegimenForOnePartnerOne(@RequestParam Long partnerId, @RequestParam int year) ;
List<Object[]> getPatientByRegimenForOnePartnerOne(@RequestParam Long partnerId, @RequestParam int year) ;


 
List<Object[]> getViralLoadMaleByRegion(@Param("year") int year);
List<Object[]> getViralLoadFemaleByRegion(@Param("year") int year);
List<Object[]> getViralLoadNothingByRegion(@Param("year") int year);
List<Object[]> getPatientMinusTenForAllRegion(@Param("year") int year);
List<Object[]>getPatientBetweenTenAndFourteenForAllRegion(@Param("year") int year);
List<Object[]> getPatientGreaterThanTwentyForAllRegion(@Param("year") int year);
List<Object[]> motifVlreasonForAllRegion(@Param("year") int year);
List<Object[]> getViralLoadMaleOtherByRegion(@Param("year") int year);
List<Object[]> getViralLoadFemaleOtherByRegion(@Param("year") int year);
List<Object[]> getViralLoadNothingOtherByRegion(@Param("year") int year);
List<Object[]> getPatientByAgeNationalForAllRegion(@Param("year") int year);
List<Object[]> getNotDeletionByRegion(@Param("year") int year);
List<Object[]> getNotDeletionByDistrict(@Param("year") int year);
List<Object[]> getNotDeletionBySite(@Param("year") int year);
List<Object[]> getNotDeletionByPartner(@Param("year") int year);
List<Object[]> getViralLoadDeletionForAllRegion(@Param("year") int year);
List<Object[]> getPatientNothingAgeForAllRegion(@Param("year") int year);

List<Object[]> getViralLoadMaleForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getViralLoadFemaleForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
List<Object[]> getViralLoadNothingForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

List<Object[]> getPatientByAgeNationalForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);

List<Object[]> getPatientMinusTenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

List<Object[]> getViralLoadByGenderForAllRegion(int year);

List<Object[]> getNotDeletionForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getNotDeletionForOneDistrict(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getNotDeletionForOneSite(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getNotDeletionForOnePartner(@Param("year") int year, @Param("regionId") Long regionId);

List<Object[]> getPatientByRegionForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

List<Object[]> getPatientByPartnerForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);
List<Object[]> getPatientByDistrictForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);
List<Object[]> getPatientBySiteForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);


List<Object[]> getPatientMaleByAgeCdcForOnRegimen(@Param("year") int year, @Param("regimenId") Long regimenId);
List<Object[]> getPatientFemaleByAgeCdcForOnRegimen(@Param("year") int year, @Param("regimenId") Long regimenId);

List<Object[]> getTestMaleByAgeCdcForOneRegimen(@Param("year") int year, @Param("regimenId") Long regimenId);
List<Object[]> getTestFemaleByAgeCdcForOneRegimen(@Param("year") int year, @Param("regimenId") Long regimenId);


List<Object[]> getPatientByAgeCdcForRegion(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientByAgeCdcForPartner(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientByAgeCdcForDistrict(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientByAgeCdcForSite(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);


//List<Object[]> getPatientByGenderForAllRegion(@Param("year") int year);
List<Gender> getPatientByGenderForAllRegion(@Param("year") int year);
List<GenderOne> getPatientByGenderForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);


List<Object[]> motifVlreasonForEntireRegion(@Param("year") int year);
List<Object[]> motifVlreasonForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);

List<Object[]> getPatientForAllNationalAgeCategory(@Param("year") int year);

List<Object[]> getPatientForAllCDCAgeCategory(@Param("year") int year);

List<Object[]> getPatientForOneNationalAgeCategory(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
List<Object[]> getPatientForOneCDCAgeCategory(@Param("year") int year, @Param("ageCategoryId") Long ageCategoryId);
  
List<Object[]> getPatientMaleForAllRegion(@Param("year") int year);  
List<Object[]> getPatientFemininForAllRegion(@Param("year") int year);
List<Object[]> getPatientNoDataForAllRegion(@Param("year") int year);
List<Object[]> getPatientMaleForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getPatientFemininForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);

List<Object[]> getPatientNoDataForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);



List<Object[]> getPatientMinusTenForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getPatientBetweenTenAndFourteenForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);
List<Object[]> getPatientGreaterThanTwentyForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);


List<Object[]> getPatientAgeNoDataForAllRegion(@Param("year") int year);
List<Object[]> getPatientAgeNoDataForOneRegion(@Param("year") int year, @Param("regionId") Long regionId);


List<Object[]> getestDBSForOneRegimen(@Param("regimenId") Long regimenId);
List<Object[]> getestPSCForOneRegimen(@Param("regimenId") Long regimenId);
List<Object[]> getestEdtaPlasmaForOneRegimen(@Param("regimenId") Long regimenId);



























































































/***********************************************************************FIN***************************************************** */











































/************************************************************************************************************************ */
//List<Object[]>getTestByCategoryAgeMinus2(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestByCategoryAgeBetweenTwoAndNine(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenTenAndFourteen(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(@Param("year") int year, @Param("partnerId") Long partnerId);
//List<Object[]>getTestBySpecificCategoryAgeGreaterThanTwentyFive(@Param("year") int year, @Param("partnerId") Long partnerId);


//List<Object[]> testCategorieOptimize(@Param("year") int year, @Param("partnerId") Long partnerId);
















































    



























}
