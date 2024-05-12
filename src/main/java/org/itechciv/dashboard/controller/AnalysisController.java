package org.itechciv.dashboard.controller;

import java.util.List;

import org.itechciv.dashboard.iservice.AnalysisService;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@ResponseBody
@CrossOrigin
public class AnalysisController {


	@Autowired
	private AnalysisService analysisServices;

     //controller pour le premier graphe par specimen EDTA

    @GetMapping("/analysisByEDTASpecimen")
    public List<Object[]> getAnalysisByspecimenEDTAData(@RequestParam int regionName) {
        return analysisServices.getAnalysisByspecimenEDTAData(regionName);
    }

    //controller pour le premier graphe par specimen DBS

    @GetMapping("/analysisByDBSSpecimen")
    public List<Object[]> getAnalysisByspecimenDBSData(@RequestParam int regionName) {
        return analysisServices.getAnalysisByspecimenDBSData(regionName);
    }

    //controller pour le premier graphe par specimen PSC

    @GetMapping("/analysisByPSCSpecimen")
    public List<Object[]> getAnalysisByspecimenPSCData(@RequestParam int regionName) {
        return analysisServices.getAnalysisByspecimenPSCData(regionName);
    }

    // CONTROLLER DISTRICT BY SPECIMEN EDTA , PSC, DBS
    @GetMapping("/districtSpecimenEDTA")
    public List<Object[]> getAnalysisBySpecimenDISTRICTEDTA(@RequestParam int districtName) {
        return analysisServices.getAnalysisBySpecimenDISTRICTEDTA(districtName);
    }


    @GetMapping("/districtSpecimenDBS")
    public List<Object[]> getAnalysisBySpecimenDISTRICTDBS(@RequestParam int districtName) {
        return analysisServices.getAnalysisBySpecimenDISTRICTDBS(districtName);
    }


    @GetMapping("/districtSpecimenPSC")
    public List<Object[]> getAnalysisBySpecimenDISTRICTPSC(@RequestParam int districtName) {
        return analysisServices.getAnalysisBySpecimenDISTRICTPSC(districtName);
    }


    //SITE dbs edta PSC
    @GetMapping("/siteSpecimenEDTA")
    public List<Object[]> getAnalysisBySpecimenSITEEDTA(@RequestParam int siteName) {
        return analysisServices.getAnalysisBySpecimenSITEEDTA(siteName);
    }

    @GetMapping("/siteSpecimenDBS")
    public List<Object[]> getAnalysisBySpecimenSITEDBS(@RequestParam int siteName) {
        return analysisServices.getAnalysisBySpecimenSITEDBS(siteName);
    }

    @GetMapping("/siteSpecimenPSC")
    public List<Object[]> getAnalysisBySpecimenSITEPSC(@RequestParam int siteName) {
        return analysisServices.getAnalysisBySpecimenSITEPSC(siteName);
    }


    //controller pour toute les regions
    @GetMapping("/regionbySpecimenEdta")
    public List<Object[]> getAnalysisByspecimenGeneralEDTAData() {
        return analysisServices.getAnalysisByspecimenGeneralEDTAData();
    }


    @GetMapping("/regionbySpecimenDBS")
    public List<Object[]> getAnalysisByspecimenGeneralDBSData() {
        return analysisServices.getAnalysisByspecimenGeneralDBSData();
    }


    @GetMapping("/regionbySpecimenPSC")
    public List<Object[]> getAnalysisByspecimenGeneralPSCData() {
        return analysisServices.getAnalysisByspecimenGeneralPSCData();
    }

    //controller  de test par chage virale pour chaque region
    @GetMapping("/yeartest")
    public List<Object[]> getAnalysisByYear(@RequestParam int year, @RequestParam Long regionId) {
        return analysisServices.getAnalysisByYear(year, regionId);
    }


    //controller  de test par chage virale pour toute les regions
    @GetMapping("/yeartestRegion")
    public List<Object[]> findByYearReg(@RequestParam int year) {
        return analysisServices.findByYearReg(year);
    }


    //controller  de test par chage virale pour chaque district
    @GetMapping("/yeartestByDistrict")
    public List<Object[]> findByYearDistrict(@RequestParam int year, @RequestParam Long districtId) {
        return analysisServices.findByYearDistrict(year, districtId);
    }


    //controller  de test par chage virale pour chaque site
    @GetMapping("/yeartestBySite")
    public List<Object[]> findByYearSite(@RequestParam int year, @RequestParam Long siteId) {
        return analysisServices.findByYearSite(year, siteId);
    }

    //controller  de test par chage virale pour chaque region par  patient
    @GetMapping("/yearchargeByPatient")
    public List<Object[]> findByYearByPatient(@RequestParam int year, @RequestParam Long regionId) {
        return analysisServices.findByYearByPatient(year, regionId);
    }

   
    //controller de patient testé par district

    @GetMapping("/yearchargeByPatientByDistrict")
    public List<Object[]> findByYearByPatientByDistrict(@RequestParam int year, @RequestParam Long districtId) {
        return analysisServices.findByYearByPatientByDistrict(year, districtId);
    }
 
    //controller de patient testé par  site
    @GetMapping("/yearchargeByPatientBySite")
    public List<Object[]> findByYearByPatientBySite(@RequestParam int year, @RequestParam Long siteId) {
        return analysisServices.findByYearByPatientBySite(year, siteId);
    }

   //controller pour toute les regions patient Testé
   @GetMapping("/yearchargeByPatientByTouteRegion")
   public List<Object[]> findByYearByPatientByTouteRegion(@RequestParam int year) {
       return analysisServices.findByYearByPatientByTouteRegion(year);
   }

  // INDICATEUR MOTIF DE LA REASON VL REASON POUR TOUTE LES  REGIONS

  @GetMapping("/motifVlByRegion")
  public List<Object[]> motifVlreasonByAllRegions(@RequestParam int year) {
      return analysisServices.motifVlreasonByAllRegions(year);
  }

 // INDICATEUR MOTIF DE LA REASON VL REASON POUR CHAQUE REGION
  @GetMapping("/motifOneRegion")
  public List<Object[]> motifVlreasonByOneRegion(@RequestParam int year, @RequestParam Long regionId) {
      return analysisServices.motifVlreasonByOneRegion(year, regionId);
  }

  // INDICATEUR MOTIF DE LA REASON VL REASON POUR CHAQUE SITE
  @GetMapping("/motifBySite")
  public List<Object[]> motifVlreasonBySite(@RequestParam int year, @RequestParam Long siteId) {
      return analysisServices.motifVlreasonBySite(year, siteId);
  }

  // INDICATEUR MOTIF DE LA REASON VL REASON POUR CHAQUE District
  @GetMapping("/motifByDistrict")
  public List<Object[]> motifVlreasonByDistrict(@RequestParam int year, @RequestParam Long districtId) {
      return analysisServices.motifVlreasonByDistrict(year, districtId);
  } 

    //controller qui calcule le nombre total test
    @GetMapping("/totaltest")
    public Long getTotalTestsByYear(@RequestParam int year) {
        return analysisServices.getTotalTestsByYear(year);
    }

    //controller qui calcule le nombre total test
    @GetMapping("/testByRegionName")
    public List<Object[]> listeTestByRegion(@RequestParam int year) {
        return analysisServices.listeTestByRegion(year);
    }

    @GetMapping("/testByDistrictName")
    public List<Object[]> listeTestByDistrict(@RequestParam int year) {
        return analysisServices.listeTestByDistrict(year);
    }


    @GetMapping("/testBySiteName")
    public List<Object[]> listeTestBySite(@RequestParam int year) {
        return analysisServices.listeTestBySite(year);
    }

    @GetMapping("/testBySIteInDistrict")
    public List<Object[]> listeTestBySiteInDistrict(@RequestParam int year, @RequestParam Long districtId) {
        return analysisServices.listeTestBySiteInDistrict(year, districtId);
    }

    // Tests par genre Masculin Feminin pour une region
    @GetMapping("/testBYGenreByOneRegionM")
    public List<Object[]> getAnalysisMaleForRegionWithSpecificYearM(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getAnalysisMaleForRegionWithSpecificYearM(regionId, year);
    }

    @GetMapping("/testBYGenreByOneRegionF")
    public List<Object[]> getAnalysisFemaleForRegionWithSpecificYear(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getAnalysisFemaleForRegionWithSpecificYear(regionId, year);
    }

    // test par genre par  district
    @GetMapping("/testbygenderbydistrictMasculin")
    public List<Object[]> getAnalysisMaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getAnalysisMaleForDistrictWithYear(districtId, year);
    }

    @GetMapping("/testbygenderfemininbydistrict")
    public List<Object[]> getAnalysisFemaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getAnalysisFemaleForDistrictWithYear(districtId, year);
    }

    // // test par genre par par site
    @GetMapping("/testbygenderbysiteMasculin")
    public List<Object[]> getAnalysisMaleForSiteWithYear(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.getAnalysisMaleForSiteWithYear(siteId, year);
    }


    @GetMapping("/testbygenderfemininbysite")
    public List<Object[]> getAnalysisFemaleForSiteWithYear(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.getAnalysisFemaleForSiteWithYear(siteId, year);
    }

    // genre pour toute les regions Feminin Masculin
    @GetMapping("/testbygendermasc")
    public List<Object[]> getAnalysisMaleByRegionWithYear(@RequestParam int year) {
        return analysisServices.getAnalysisMaleByRegionWithYear( year);
    }

    @GetMapping("/testbygenderfem")
    public List<Object[]> getAnalysisFemaleByRegionWithYear(@RequestParam int year) {
        return analysisServices.getAnalysisFemaleByRegionWithYear( year);
    }

    // PATIENT TESTE PAR GENRE POUR TOUTE LES REGIONS
    @GetMapping("/patientbygendermasc")
    public List<Object[]> getAnalysisPatientMaleByRegionWithSpecificYear(@RequestParam int year) {
        return analysisServices.getAnalysisPatientMaleByRegionWithSpecificYear( year);
    }

    @GetMapping("/patientbygenderfem")
    public List<Object[]> getAnalysisPatientFemaleByRegionWithSpecificYear(@RequestParam int year) {
        return analysisServices.getAnalysisPatientFemaleByRegionWithSpecificYear( year);
    }

   /*********************************************************************************************************************************/

        @GetMapping("/patientbyageminustwo")
        public List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeMinusTwoForOneRegion(regionId, year );

        }

        @GetMapping("/patientbyagetwoandnine")
        public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTwoAndNineForOneRegion(regionId, year );

        }

        @GetMapping("/patientbyagetenandfourteen")
        public List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(regionId, year );


        }

        @GetMapping("/patientbyagefifteennineteen")
        public List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(regionId, year );

        }

        @GetMapping("/patientbyagetwentyandtwentyfour")
        public List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(regionId, year );

        }

        @GetMapping("/patientbyagegreaterthantwentyfive")
        public List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(regionId, year );
        }

        @GetMapping("/patientbyageminustwoforallregion")
        public List<Object[]> getTestedPatientByAgeMinusTwoForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeMinusTwoForAllRegion(year );
        }


        @GetMapping("/patientbyagetwoandnineforallregion")
        public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTwoAndNineForAllRegion(year );
        }


        @GetMapping("/patientbyagetenandfourteenforallregion")
        public List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(year );
        }

        @GetMapping("/patientbyagefifteenandnineteenforallregion")
        public List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(year );
        }

        @GetMapping("/patientbyagetwentyandtwentyfourallregion")
        public List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(year );
        }

        @GetMapping("/patientbyagegreaterthantwentyfiverallregion")
        public List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(@RequestParam int year) {
        return analysisServices.getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(year );
        }

        @GetMapping("/a")
        public List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryMinusTwoForOneRegion(regionId, year );
        }

        @GetMapping("/b")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(regionId, year );
        }

        @GetMapping("/c")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(regionId, year );
        }

        @GetMapping("/d")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(regionId, year );
        }

        @GetMapping("/e")
        public List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(regionId, year );
        }

        @GetMapping("/f")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(regionId, year );
        }

        @GetMapping("/g")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(regionId, year );
        }


        @GetMapping("/h")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(regionId, year );
        }

        @GetMapping("/i")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(regionId, year );
        }

        @GetMapping("/j")
        public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(regionId, year );
        }

        @GetMapping("/k")
        public List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(regionId, year );
        }

        @GetMapping("/l")
        public List<Object[]> getPatientTestedByAgeCategoryMinusTwoForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryMinusTwoForAllRegion(year );
        }


        @GetMapping("/m")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(year );
        }


        @GetMapping("/n")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(year );
        }

        @GetMapping("/o")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(year );
        }

        @GetMapping("/p")
        public List<Object[]> getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(year );

        } 
        
        @GetMapping("/q")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(year );
        }


        @GetMapping("/r")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(year );
        }


        @GetMapping("/s")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(year );
        }


        @GetMapping("/t")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(year );
        }


        @GetMapping("/u")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(year );
        }


        @GetMapping("/v")
        public List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(year );
        }


        @GetMapping("/w")
        public List<Object[]> getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(@RequestParam int year) {
        return analysisServices.getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(year );
        }


        @GetMapping("/x")
        public List<Object[]> getTestByAgeMinusTwoForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeMinusTwoForOneRegion(regionId, year );
        }


        @GetMapping("/y")
        public List<Object[]> getTestByAgeBetweenTwoAndNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTwoAndNineForOneRegion(regionId, year );
        }


        @GetMapping("/z")
        public List<Object[]> getTestByAgeBetweenTenAndFourteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTenAndFourteenForOneRegion(regionId, year );
        }


        @GetMapping("/aa")
        public List<Object[]> getTestByAgeBetweenFifteenAndNineteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeBetweenFifteenAndNineteenForOneRegion(regionId, year );
        }


        @GetMapping("/ab")
        public List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(regionId, year );
        }



        @GetMapping("/ac")
        public List<Object[]> getTestByAgeGreaterThanTwentyFiveForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeGreaterThanTwentyFiveForOneRegion(regionId, year );
        }


        @GetMapping("/ad")
        public List<Object[]> getTestByAgeMinusTwoForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeMinusTwoForAllRegion(year );
        }


        @GetMapping("/ae")
        public List<Object[]> getTestByAgeBetweenTwoAndNineForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTwoAndNineForAllRegion(year );
        }


        @GetMapping("/af")
        public List<Object[]> getTestByAgeBetweenTenAndFourteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTenAndFourteenForAllRegion(year );
        }


        @GetMapping("/ag")
        public List<Object[]> getTestByAgeBetweenFifteenAndNineteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeBetweenFifteenAndNineteenForAllRegion(year );
        }



        @GetMapping("/ah")
        public List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(year );
        }



        @GetMapping("/ai")
        public List<Object[]> getTestByAgeGreaterThanTwentyFiveForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeGreaterThanTwentyFiveForAllRegion(year );
        }


        @GetMapping("/aj")
        public List<Object[]> getTestByAgeCategoryBetweenOneAndFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenOneAndFourForOneRegion(regionId, year );
        }


        @GetMapping("/ak")
        public List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFiveAndNineForOneRegion(regionId, year );
        }



        @GetMapping("/al")
        public List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(regionId, year );
        }



        @GetMapping("/am")
        public List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(regionId, year );
        }



        @GetMapping("/an")
        public List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(regionId, year );
        }



        @GetMapping("/ao")
        public List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(regionId, year );
        }


        @GetMapping("/ap")
        public List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(regionId, year );
        }


        @GetMapping("/aq")
        public List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(regionId, year );
        }

        @GetMapping("/ar")
        public List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(regionId, year );
        }


        @GetMapping("/as")
        public List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(regionId, year );
        }


        @GetMapping("/at")
        public List<Object[]> getTestByAgeCategoryMinusTwoForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryMinusTwoForAllRegion(year );
        }


        @GetMapping("/au")
        public List<Object[]> getTestByAgeCategoryBetweenOneAndFourForAllegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenOneAndFourForAllegion(year );
        }


        @GetMapping("/av")
        public List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFiveAndNineForAllRegion( year );
        }


        @GetMapping("/aw")
        public List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(year );
        }


        @GetMapping("/ax")
        public List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(year );
        }


        @GetMapping("/ay")
        public List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion( year );
        }


        @GetMapping("/az")
        public List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion( year );
        }


        @GetMapping("/ba")
        public List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(year );
        }


        @GetMapping("/bc")
        public List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(year );
        }


        @GetMapping("/be")
        public List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(year );
        }


        @GetMapping("/bh")
        public List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(year );
        }



        @GetMapping("/bi")
        public List<Object[]> getTestByAgeCategoryGreaterThanFiftyForAllRegion(@RequestParam int year) {
        return analysisServices.getTestByAgeCategoryGreaterThanFiftyForAllRegion(year );
        }
































}
