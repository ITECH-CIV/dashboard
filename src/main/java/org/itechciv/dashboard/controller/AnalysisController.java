package org.itechciv.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.itechciv.dashboard.helper.CategoryAge;
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


        //Nombre de tests réalisés par tranche d'âge
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

/************************************************************************************************************************************ */

    //indicateur patient testé par genre dans district

    @GetMapping("/patientbygenderbydistrictMasculin")
    public List<Object[]> getAnalysisPatientMaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientMaleForDistrictWithYear(districtId, year);
    }

    @GetMapping("/patientbygenderfemininbydistrict")
    public List<Object[]> getAnalysisPatientFemaleForDistrictWithYear(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientFemaleForDistrictWithYear(districtId, year);
    }

    // pour chaque region

    @GetMapping("/patientbygenderbyoneregionMasculin")
    public List<Object[]> getAnalysisPatientMaleForRegionWithYearM(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientMaleForRegionWithYearM(regionId, year);
    }

    @GetMapping("/patientbygenderfemininbychaqueregion")
    public List<Object[]> getAnalysisPatientFemaleForRegionWithYearF(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientFemaleForRegionWithYearF(regionId, year);
    }

    // par site

    @GetMapping("/patientbygenderbysitemasculin")
    public List<Object[]> getAnalysisPatientMaleForSiteWithYearSiteM(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientMaleForSiteWithYearSiteM(siteId, year);
    }

    @GetMapping("/patientbygenderfemininbysite")
    public List<Object[]> getAnalysisPatientFemaleForSiteWithYearsiteF(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.getAnalysisPatientFemaleForSiteWithYearsiteF(siteId, year);
    }

/********************************************************************************************************************************** */
    //NOMBRE DE TESTS REALISES PAR TRANCHE D'AGE
        
    // Tendances de Tests REalises par charge virale
    @GetMapping("/tendancetest2021")
    public List<Object[]> tendancetest2021() {
        return analysisServices.tendancetest2021();
    }

    @GetMapping("/tendancetest2022")
    public List<Object[]> tendancetest2022() {
        return analysisServices.tendancetest2022();
    }

    @GetMapping("/tendancetest2023")
    public List<Object[]> tendancetest2023() {
        return analysisServices.tendancetest2023();
    }


//test par tranche d age pour chaque region
    @GetMapping("/trancheageoneregion")
    public List<Object[]> yourMethodName(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodName(regionId,year);
    }


    @GetMapping("/trancheageoneregionone")
    public List<Object[]> yourMethodNameOne(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameOne(regionId,year);
    }

    @GetMapping("/trancheageoneregiontwo")
    public List<Object[]> yourMethodNametwo(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNametwo(regionId,year);
    }


    @GetMapping("/trancheageoneregionthree")
    public List<Object[]> yourMethodNamethree(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNamethree(regionId,year);
    }


    @GetMapping("/trancheageoneregionfour")
    public List<Object[]> yourMethodNamefour(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNamefour(regionId,year);
    }

    @GetMapping("/trancheageoneregionfive")
    public List<Object[]> yourMethodNameSix(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameSix(regionId,year);
    }

    @GetMapping("/trancheageoneregionsix")
    public List<Object[]> yourMethodNameseven(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameseven(regionId,year);
    }


    @GetMapping("/trancheageoneregionseven")
    public List<Object[]> yourMethodNameheight(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameheight(regionId,year);
    }


    @GetMapping("/trancheageoneregionheight")
    public List<Object[]> yourMethodNamenine(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNamenine(regionId,year);
    }


    @GetMapping("/trancheageoneregionnine")
    public List<Object[]> yourMethodNameten(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameten(regionId,year);
    }


    @GetMapping("/trancheageoneregionten")
    public List<Object[]> yourMethodNameeleven(@RequestParam Long regionId, @RequestParam int year) {
        return analysisServices.yourMethodNameeleven(regionId,year);
    }

   //controller pour test realisés par tranche d age pour chaque district
    @GetMapping("/trancheagedistrict")
    public List<Object[]> yourMethodDistrictName(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictName(districtId,year);
    }

    @GetMapping("/trancheagedistrictone")
    public List<Object[]> yourMethodDistrictNameOne(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameOne(districtId,year);
    }

    @GetMapping("/trancheagedistricttwo")
    public List<Object[]> yourMethodDistrictNametwo(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNametwo(districtId,year);
    }


    @GetMapping("/trancheagedistrictthree")
    public List<Object[]> yourMethodDistrictNamethree(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNamethree(districtId,year);
    }


    @GetMapping("/trancheagedistrictfour")
    public List<Object[]> yourMethodDistrictNamefour(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNamefour(districtId,year);
    }

    @GetMapping("/trancheagedistrictfive")
    public List<Object[]> yourMethodDistrictNameSix(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameSix(districtId,year);
    }

    @GetMapping("/trancheagedistrictsix")
    public List<Object[]> yourMethodDistrictNameseven(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameseven(districtId,year);
    }

    @GetMapping("/trancheagedistrictseven")
    public List<Object[]> yourMethodDistrictNameheight(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameheight(districtId,year);
    }

    @GetMapping("/trancheagedistrictheight")
    public List<Object[]> yourMethodDistrictNamenine(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNamenine(districtId,year);
    }


    @GetMapping("/trancheagedistrictnine")
    public List<Object[]> yourMethodDistrictNameten(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameten(districtId,year);
    }


    @GetMapping("/trancheagedistrictten")
    public List<Object[]> yourMethodDistrictNameeleven(@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.yourMethodDistrictNameeleven(districtId,year);
    }

    //controller pour test realisés par tranche d age pour chaque site

    @GetMapping("/trancheagesite")
    public List<Object[]> yourMethodSiteName(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteName(siteId,year);
    }


    @GetMapping("/trancheagesiteone")
    public List<Object[]> yourMethodSiteNameOne(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameOne(siteId,year);
    }

    @GetMapping("/trancheagesitetwo")
    public List<Object[]> yourMethodSiteNametwo(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNametwo(siteId,year);
    }


    @GetMapping("/trancheagesitethree")
    public List<Object[]> yourMethodSiteNamethree(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNamethree(siteId,year);
    }


    @GetMapping("/trancheagesitefour")
    public List<Object[]> yourMethodSiteNamefour(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNamefour(siteId,year);
    }

    @GetMapping("/trancheagesitefive")
    public List<Object[]> yourMethodSiteNameSix(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameSix(siteId,year);
    }

    @GetMapping("/trancheagesitesix")
    public List<Object[]> yourMethodSiteNameseven(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameseven(siteId,year);
    }

    @GetMapping("/trancheagesiteseven")
    public List<Object[]> yourMethodSiteNameheight(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameheight(siteId,year);
    }


    @GetMapping("/trancheagesiteheight")
    public List<Object[]> yourMethodSiteNamenine(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNamenine(siteId,year);
    }


    @GetMapping("/trancheagesitenine")
    public List<Object[]> yourMethodSiteNameten(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameten(siteId,year);
    }

    @GetMapping("/trancheagesiteten")
    public List<Object[]> yourMethodSiteNameeleven(@RequestParam Long siteId, @RequestParam int year) {
        return analysisServices.yourMethodSiteNameeleven(siteId,year);
    }

 //pour toute les regions
    @GetMapping("/trancheagereg")
    public List<Object[]> MethodRegion(@RequestParam int year) {
        return analysisServices.MethodRegion(year);
    }


    @GetMapping("/trancheageregone")
    public List<Object[]> MethodRegionOne( @RequestParam int year) {
        return analysisServices.MethodRegionOne(year);
    }

    @GetMapping("/trancheageregtwo")
    public List<Object[]> MethodRegionThree( @RequestParam int year) {
        return analysisServices.MethodRegionThree(year);
    }


    @GetMapping("/trancheageregthree")
    public List<Object[]> MethodRegionfour( @RequestParam int year) {
        return analysisServices.MethodRegionfour(year);
    }


    @GetMapping("/trancheageregfour")
    public List<Object[]> MethodRegionfive(@RequestParam int year) {
        return analysisServices.MethodRegionfive(year);
    }


    @GetMapping("/trancheageregfive")
    public List<Object[]> MethodRegionsix(@RequestParam int year) {
        return analysisServices.MethodRegionsix(year);
    }

    @GetMapping("/trancheageregsix")
    public List<Object[]> MethodRegionseven( @RequestParam int year) {
        return analysisServices.MethodRegionseven(year);
    }


    @GetMapping("/trancheageregseven")
    public List<Object[]> MethodRegionheight( @RequestParam int year) {
        return analysisServices.MethodRegionheight(year);
    }

    @GetMapping("/trancheageregheight")
    public List<Object[]> MethodRegionnine( @RequestParam int year) {
        return analysisServices.MethodRegionnine(year);
    }

    @GetMapping("/trancheageregnine")
    public List<Object[]> MethodRegionten( @RequestParam int year) {
        return analysisServices.MethodRegionten(year);
    }


    @GetMapping("/trancheageregten")
    public List<Object[]> MethodRegioneleven( @RequestParam int year) {
        return analysisServices.MethodRegioneleven(year);
    }

    @GetMapping("/trancheageregeleven")
    public List<Object[]> MethodRegiontwelve( @RequestParam int year) {
        return analysisServices.MethodRegiontwelve(year);
    }
    /************************************************************************************************************************* */
    @GetMapping("/patientforregion")
    public List<Object[]> getPatientForAllRegion( @RequestParam int year) {
        return analysisServices.getPatientForAllRegion(year);
    }

    @GetMapping("/patientbydistrict")
    public List<Object[]> getPatientForDistrict (@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getPatientForDistrict(districtId, year);
    }

    @GetMapping("/patientbydistrictwithsite")
    public List<Object[]> getPatientForDistrictWithSite (@RequestParam Long districtId, @RequestParam int year) {
        return analysisServices.getPatientForDistrictWithSite(districtId, year);
    }

    @GetMapping("/patientbyagetwoandnineforonedistrict")
    public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(@RequestParam Long districtId, @RequestParam int year) {
    return analysisServices.getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(districtId, year);
    }

    @GetMapping("/patientbyagetwoandnineforonesite")
    public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneSite(@RequestParam Long siteId, @RequestParam int year) {
    return analysisServices.getTestedPatientByAgeBetweenTwoAndNineForOneSite(siteId, year);
    }

    @GetMapping("/testbysiteforregion")
    public List<Object[]> getTestBySiteForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
    return analysisServices.getTestBySiteForOneRegion(regionId, year);
    }

    @GetMapping("/patientbysiteforregion")
    public List<Object[]> getPatientBySiteForOneRegion(@RequestParam Long regionId, @RequestParam int year) {
    return analysisServices.getPatientBySiteForOneRegion(regionId, year);
    }

    @GetMapping("/testbypartner")
    public List<Object[]> getTestByPartner(@RequestParam int year) {
    return analysisServices.getTestByPartner(year);
    }

    @GetMapping("/patientbypartner")
    public List<Object[]> getPatientByPartner(@RequestParam int year) {
    return analysisServices.getPatientByPartner(year);
    }


    @GetMapping("/testyspecimenedta")
    public List<Object[]> getestByspecimenEDTAPlasma(@RequestParam int partnerId) {
    return analysisServices.getestByspecimenEDTAPlasma(partnerId);
    }

    @GetMapping("/testbyspecimendbs")
    public List<Object[]> getestByspecimenDBS(@RequestParam int partnerId) {
    return analysisServices.getestByspecimenDBS(partnerId);
    }


    @GetMapping("/testbyspecimenpsc")
    public List<Object[]> getestByspecimenPSC(@RequestParam int partnerId) {
    return analysisServices.getestByspecimenPSC(partnerId);
    }

    @GetMapping("/patientminusone")
    public List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(@RequestParam int year) {
    return analysisServices.getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(year);
    }

    @GetMapping("/patientbydistrictm")
    public List<Object[]> district7(@RequestParam Long districtId, @RequestParam int year) {
    return analysisServices.district7(districtId, year);
    }

    @GetMapping("/patientbysitem")
    public List<Object[]> site8(@RequestParam Long siteId, @RequestParam int year) {
    return analysisServices.site8(siteId, year);
    }

    @GetMapping("/testdetailbypartner")
    public List<Object[]> getTestWithDetailsByPartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestWithDetailsByPartner(year, partnerId);
    }

    @GetMapping("/patientdetailbypartner")
    public List<Object[]> getPatientWithDetailsByPartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientWithDetailsByPartner(year, partnerId);
    }


    @GetMapping("/testbydistrictother")
    public List<Object[]> districtSeven(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.districtSeven(partnerId, year);
    }


    @GetMapping("/testallregionrone")
    public List<Object[]> testOne(@RequestParam int year) {
    return analysisServices.testOne(year);
    }

   @GetMapping("/testallregiontwo")
    public List<Object[]> testTwo(@RequestParam int year) {
    return analysisServices.testTwo(year);
    }
 

    @GetMapping("/testallregionthree")
    public List<Object[]> testThree(@RequestParam int year) {
    return analysisServices.testThree(year);
    }
 

    @GetMapping("/testallregionfour")
    public List<Object[]> testFour(@RequestParam int year) {
    return analysisServices.testFour(year);
    }
 

    @GetMapping("/testallregionfive")
    public List<Object[]> testFive(@RequestParam int year) {
    return analysisServices.testFive(year);
    }
 

    @GetMapping("/testallregionsix")
    public List<Object[]> testSix(@RequestParam int year) {
    return analysisServices.testSix(year);
    }
  

    @GetMapping("/testbydistrictotherone")
    public List<Object[]> districtEleven(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.districtEleven(partnerId, year);
    }



    @GetMapping("/patientallregionone")
    public List<Object[]> patientOne(@RequestParam int year) {
    return analysisServices.patientOne(year);
    }

    @GetMapping("/patientallregiontwo")
    public List<Object[]> patientTwo(@RequestParam int year) {
    return analysisServices.patientTwo(year);
    }
 
    @GetMapping("/patientallregionthree")
    public List<Object[]> patientThree(@RequestParam int year) {
    return analysisServices.patientThree(year);
    }
 

    @GetMapping("/patientallregionfour")
    public List<Object[]> patientFour(@RequestParam int year) {
    return analysisServices.patientFour(year);
    }
 
    @GetMapping("/patientallregionfive")
    public List<Object[]> patientFive(@RequestParam int year) {
    return analysisServices.patientFive(year);
    }
 
    @GetMapping("/patientallregionsix")
    public List<Object[]> patientSix(@RequestParam int year) {
    return analysisServices.patientSix(year);
    }

    @GetMapping("/testforspecificpartner")
    public List<Object[]> getTestForSpecificPartner(@RequestParam int year,  @RequestParam Long partnerId) {
    return analysisServices.getTestForSpecificPartner(year, partnerId);
    }

    @GetMapping("/patientforspecificpartner")
    public List<Object[]> getPatientForSpecificPartner(@RequestParam int year,  @RequestParam Long partnerId) {
    return analysisServices.getPatientForSpecificPartner(year, partnerId);
    }

    @GetMapping("/patientmaleforspecificpartner")
    public List<Object[]> getPatientForOnePartnerByMale(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientForOnePartnerByMale(year, partnerId);
    }

    @GetMapping("/patientfemaleforspecificpartner")
    public List<Object[]> getPatientForOnePartnerByFemale(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientForOnePartnerByFemale(year, partnerId);
    }

    @GetMapping("/testmaleforspecificpartner")
    public List<Object[]> getTestForOnePartnerByMale(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestForOnePartnerByMale(year, partnerId);
    }

    @GetMapping("/testfemaleforspecificpartner")
    public List<Object[]> getTestForOnePartnerByFemale(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestForOnePartnerByFemale(year, partnerId);
    }

    @GetMapping("/motifvlreasonbyonepartner")
    public List<Object[]> motifVlreasonByOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.motifVlreasonByOnePartner(year, partnerId);
    }
 
    @GetMapping("/testbysiteforonepartner")
    public List<Object[]> getTestBySiteForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestBySiteForOnePartner(partnerId, year);
    }


    @GetMapping("/testbycdcforonepartner")
    public List<Object[]> geTestByCategoryCDCForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.geTestByCategoryCDCForOnePartner(partnerId, year);
    }

    
    @GetMapping("/testbynationalforonepartner")
    public List<Object[]> geTestByCategoryNationalForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.geTestByCategoryNationalForOnePartner(partnerId, year);
    }


    @GetMapping("/patientbycdcforonepartner")
    public List<Object[]> getPatientByCategoryCdciForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientByCategoryCdciForOnePartner(partnerId, year);
    }


    @GetMapping("/patientbynationalforonepartner")
    public List<Object[]> getPatientByCategoryNationalForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientByCategoryNationalForOnePartner(partnerId, year);
    }


    @GetMapping("/testbyregimenforallpartner")
    public List<Object[]> getTestByRegimenForAllPartner(@RequestParam int year) {
    return analysisServices.getTestByRegimenForAllPartner(year);
    }


    @GetMapping("/patientbyregimenforallpartner")
    public List<Object[]> getPatientByRegimenForAllPartner(@RequestParam int year) {
    return analysisServices.getPatientByRegimenForAllPartner(year);
    }


    @GetMapping("/testbyregimenforonepartner")
    public List<Object[]> getTestByRegimenForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestByRegimenForOnePartner(partnerId, year);
    }


    @GetMapping("/patientbyregimenforonepartner")
    public List<Object[]> getPatientByRegimenForOnePartner(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getPatientByRegimenForOnePartner(partnerId, year);
    }


    @GetMapping("/testdbsforoneregimen")
    public List<Object[]> getestBySpecimenDBSForOneRegimen(@RequestParam Long regimenId) {
    return analysisServices.getestBySpecimenDBSForOneRegimen(regimenId);
    }

    @GetMapping("/testpscforoneregimen")
    public List<Object[]> getestBySpecimenPSCForOneRegimen(@RequestParam Long regimenId) {
    return analysisServices.getestBySpecimenPSCForOneRegimen(regimenId);
    }

    @GetMapping("/testedtaforoneregimen")
    public List<Object[]> getestBySpecimenEdtaPlasmaForOneRegimen(@RequestParam Long regimenId) {
    return analysisServices.getestBySpecimenEdtaPlasmaForOneRegimen(regimenId);
    }


    @GetMapping("/testforoneregimen")
    public List<Object[]> getTestForOneRegimen(@RequestParam Long regimenId, @RequestParam int year) {
    return analysisServices.getTestForOneRegimen(regimenId, year);
    }

    @GetMapping("/testbycdcmaleforoneregimen")
    public List<Object[]> getTestByCDCMaleForOneRegimen(@RequestParam Long regimenId, @RequestParam int year) {
    return analysisServices.getTestByCDCMaleForOneRegimen(regimenId, year);
    }


    @GetMapping("/testbycdcfemaleforoneregimen")
    public List<Object[]> getTestByCdcFemaleForOneRegimen(@RequestParam Long regimenId, @RequestParam int year) {
    return analysisServices.getTestByCdcFemaleForOneRegimen(regimenId, year);
    }


    @GetMapping("/patientbycdcmaleforoneregimen")
    public List<Object[]> getPatientByCDCMaleForOneRegimen(@RequestParam Long regimenId, @RequestParam int year) {
    return analysisServices.getPatientByCDCMaleForOneRegimen(regimenId, year);
    }

    @GetMapping("/patientbycdcfemininforoneregimen")
    public List<Object[]> getPatientByCDCFemaleForOneRegimen(@RequestParam Long regimenId, @RequestParam int year) {
    return analysisServices.getPatientByCDCFemaleForOneRegimen(regimenId, year);
    }


    @GetMapping("/testbyspecimendbsforregimenandpartnerone")
    public List<Object[]> getestBySpecimenDBSForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId) {
    return analysisServices.getestBySpecimenDBSForRegimenAndPartnerOne(regimenId, partnerId);
    }


    @GetMapping("/testbyspecimenpscforregimenandpartnerone")
    public List<Object[]> getestBySpecimenPSCForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId) {
    return analysisServices.getestBySpecimenPSCForRegimenAndPartnerOne(regimenId, partnerId);
    }


    @GetMapping("/testbyspecimenedtaforregimenandpartnerone")
    public List<Object[]> getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId) {
    return analysisServices.getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(regimenId, partnerId);
    }


    @GetMapping("/testforregimenandpartnerone")
    public List<Object[]> getTestForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId, @RequestParam int year) {
    return analysisServices.getTestForRegimenAndPartnerOne(regimenId, partnerId, year);
    }

    @GetMapping("/testbycdcmaleforregimenandpartnerone")
    public List<Object[]> getTestByCDCMaleForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId, @RequestParam int year) {
    return analysisServices.getTestByCDCMaleForRegimenAndPartnerOne(regimenId, partnerId, year);
    }


    @GetMapping("/testbycdcfemaleforregimenandpartnerone")
    public List<Object[]> getTestByCDCFemaleForRegimenAndPartnerOne(@RequestParam Long regimenId, @RequestParam Long partnerId, @RequestParam int year) {
    return analysisServices.getTestByCDCFemaleForRegimenAndPartnerOne(regimenId, partnerId, year);
    }


    @GetMapping("/testandpatientresultforpartnerandregion")
    public List<Object[]> getTestAndPatientResultForPartnerAndRegion(@RequestParam int year) {
    return analysisServices.getTestAndPatientResultForPartnerAndRegion(year);
    }


    @GetMapping("/testandpatientresultforonepartnerandregion")
    public List<Object[]> getTestAndPatientResultForOnePartnerAndRegion(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestAndPatientResultForOnePartnerAndRegion(year, partnerId);
    }

    @GetMapping("/testandpatientresultforalllab")
    public List<Object[]> getTestAndPatientResultForAllLab(@RequestParam int year) {
    return analysisServices.getTestAndPatientResultForAllLab(year);
    }


    @GetMapping("/testandpatientresultforonelab")
    public List<Object[]> getTestAndPatientResultForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getTestAndPatientResultForOneLab(year, labId);
    }
/************************************************************************************************************************** */

    @GetMapping("/testandpatientbyspecimendbsforalllab")
    public List<Object[]> getestAndPatientBySpecimenDBSForAllLab(@RequestParam int year) {
    return analysisServices.getestAndPatientBySpecimenDBSForAllLab(year);
    }


    @GetMapping("/testandpatientbyspecimenpscforalllab")
    public List<Object[]> getestAndPatientBySpecimenPSCForAllLab(@RequestParam int year) {
    return analysisServices.getestAndPatientBySpecimenPSCForAllLab(year);
    }


    @GetMapping("/testandpatientbyspecimenedtaforalllab")
    public List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForAllLab(@RequestParam int year) {
    return analysisServices.getestAndPatientBySpecimenEdtaPlasmaForAllLab(year);
    }


    @GetMapping("/testandpatientbyspecimendbsforonelab")
    public List<Object[]> getestAndPatientBySpecimenDBSForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getestAndPatientBySpecimenDBSForOneLab(year, labId);
    }

    @GetMapping("/testandpatientbyspecimenpscforonelab")
    public List<Object[]> getestAndPatientBySpecimenPSCForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getestAndPatientBySpecimenPSCForOneLab(year, labId);
    }

    @GetMapping("/testandpatientbyspecimenedtaforonelab")
    public List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getestAndPatientBySpecimenEdtaPlasmaForOneLab(year, labId);
    }


    @GetMapping("/testforalllab")
    public List<Object[]> getTestForAllLab(@RequestParam int year) {
    return analysisServices.getTestForAllLab(year);
    }

    @GetMapping("/testforonelab")
    public List<Object[]> getTestForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getTestForOneLab(year, labId);
    }

    @GetMapping("/patientforalllab")
    public List<Object[]> getPatientForAllLab(@RequestParam int year) {
    return analysisServices.getPatientForAllLab(year);
    }

    @GetMapping("/patientforonelab")
    public List<Object[]> getPatientForOneLab(@RequestParam int year, @RequestParam Long labId) {
    return analysisServices.getPatientForOneLab(year, labId);
    }

    @GetMapping("/patientbycdcmaleandallpartner")
    public List<Object[]> getPatientByCDCMaleAndPartnerAll(@RequestParam int year) {
    return analysisServices.getPatientByCDCMaleAndPartnerAll(year);
    }


/* 
    @GetMapping("/testcategoryone")
    public List<Object[]> getTestByCategoryAgeMinus2(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestByCategoryAgeMinus2(year);
    }
 */

/* 
    @GetMapping("/testcategorytwo")
    public List<Object[]> getTestByCategoryAgeBetweenTwoAndNine(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestByCategoryAgeBetweenTwoAndNine(year);
    }
 */
/* 

    @GetMapping("/testcategorythree")
    public List<Object[]> getTestBySpecificCategoryAgeBetweenTenAndFourteen(@RequestParam int year, @RequestParam Long partnerId) {
    return analysisServices.getTestBySpecificCategoryAgeBetweenTenAndFourteen(year);
    }
 */

/* 
    @GetMapping("/testcategoryfour")
    public List<Object[]> getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(@RequestParam int year) {
    return analysisServices.getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(year);
    }
 */

/* 
    @GetMapping("/testcategoryfive")
    public List<Object[]> getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(@RequestParam int year) {
    return analysisServices.getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(year);
    }
 */

/* 
    @GetMapping("/testcategorysix")
    public List<Object[]> getTestBySpecificCategoryAgeGreaterThanTwentyFive(@RequestParam int year) {
    return analysisServices.getTestBySpecificCategoryAgeGreaterThanTwentyFive(year);
    }
 */

/* 
    @GetMapping("/testcategoryoptimize")
    public List<Object[]> testCategorieOptimize(@RequestParam int year) {
    return analysisServices.testCategorieOptimize(year);
    }
 */


 
 
 
 































 
/*
    @GetMapping("/count/inferieurmil")
    public long countTestsWithViralLoadLessThan1000() {
        return analysisServices.countTestsWithViralLoadLessThan1000();
    }


    @GetMapping("/count/inferieurll")
    public long countTestWithViralLoadLL() {
        return analysisServices.countTestWithViralLoadLL();
    }


    @GetMapping("/count/supegal")
    public long countTestWithViralLoadSupegal() {
        return analysisServices.countTestWithViralLoadSupegal();
    }


    //varialLoad Invalide


    @GetMapping("/count/viraleLoadInvalide")
    public long countTestInvalideviralLoad() {
        return analysisServices.countTestInvalideviralLoad();
    }


    @GetMapping("/count/{regionName}")
    public int countTestsByRegionAndViralLoad(@PathVariable String regionName) {
        return analysisServices.countTestsByRegionAndViralLoad(regionName);
    }

    @GetMapping("/countAnalysis")
    public int countTestsByRegionDateAndSampleType(
            @PathVariable String regionName,
            Date testDate) {
        return analysisServices.countTestsByRegionDateAndSampleType(regionName, testDate);
    }

    @GetMapping("/dashboard/data")
    public List<Object[]> getDashboardData(
            //public List<Object[]> getDashboardData(
            @RequestParam String regionName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date testDate
    ) {
        return analysisServices.getDashboardData(regionName, testDate);
    }


    //controller

    @GetMapping("/testCount")


    public ResponseEntity<List<Object[]>> getTestCountsByMonth(
            @RequestParam("regionName") String regionName,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") Date startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") Date endDate) {

        List<Object[]> result = analysisServices.getTestCountsByMonth(regionName, startDate, endDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //pieChart

    //inferieur LL par region

    @GetMapping("/testsWithLL/{regionName}")
    public List<Object[]> TestsWithViralLoadLessThanLL(@PathVariable String regionName) {
        return analysisServices.TestsWithViralLoadLessThanLL(regionName);
    }



   //>=1000

    @GetMapping("/supMill/{regionName}")
    public List<Object[]> TestsWithViralLoadLessThansup1000(@PathVariable String regionName) {
        return analysisServices.TestsWithViralLoadLessThansup1000(regionName);
    }



    //<1000 par region

    @GetMapping("/infMill/{regionName}")
    public List<Object[]> TestsWithViralLoadLessThanInf1000(@PathVariable String regionName) {
        return analysisServices.TestsWithViralLoadLessThanInf1000(regionName);
    }




    @GetMapping("/difMill/{regionName}")
    public List<Object[]> TestsWithViralLoadLessThanDif1000(@PathVariable String regionName) {
        return analysisServices.TestsWithViralLoadLessThanDif1000(regionName);
    }


    //controlleur pour le gender

    @GetMapping("/gender/count")
    public List<Object[]> countTestsByYearAndGender(
            @RequestParam String regionName,
            @RequestParam int year
    ) {
        return analysisServices.countTestsByYearAndGender(regionName, year);
    }



    //controller pour le supprimé

    @GetMapping("/supprime/totalTests")
    public List<Object[]> getTotalTestsByYearAndSupprime(@RequestParam("regionName") String regionName) {
        return analysisServices.getTotalTestsByYearAndSupprime(regionName);
    }



    //non supprime

    @GetMapping("/nonsupprime/totalTests")

    public List<Object[]> getTotalTestsByYearAndGenderNonSupprime(@RequestParam("regionName") String regionName) {
        return analysisServices.getTotalTestsByYearAndGenderNonSupprime(regionName);
    }



    //nouveau qui renvoie toute les donnees



    @GetMapping("/totalgenderSup/test")
    public List<Object[]> getTotalTestsByYearAndSupp(@RequestParam("regionName") String regionName) {
        return analysisServices.getTotalTestsByYearAndSupp(regionName);
    }


    @GetMapping("/genre/total")
    public List<Object[]> getTotalTestsByTesterByGenreFM(@RequestParam("regionName") String regionName) {
        return analysisServices.getTotalTestsByTesterByGenreFM(regionName);
    }



    @GetMapping("/dashboard/reponsebymonth")
    public ResponseEntity<?> getAnalysisData(@RequestParam("regionName") String regionName) {
        try {
            List<Object[]> analysisData = analysisServices.getAnalysisData(regionName);
            return ResponseEntity.ok().body(analysisData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/test/dataG")
    public List<Object[]> getAnalysisTestData() {
        return analysisServices.getAnalysisTestDatar();
    }

    //controller  pour le nombre de tests realisés par site de la region

    @GetMapping("/sitedelaregion")
    public List<Object[]> getAnalysisDataByRegion(@RequestParam("regionName") String regionName) {
        return analysisServices.getAnalysisDataByRegion(regionName);
    }


    @GetMapping("/patientByregion")
    public List<Object[]> getAnalysisDataByPatientRegion(@RequestParam("regionName") String regionName) {
        return analysisServices.getAnalysisDataByPatientRegion(regionName);
    }


  //controlleur pour raisoon ou motif
    @GetMapping("/reasons")
    public List<Object[]> countReasonsByRegionmotif(@RequestParam String regionName) {
        return analysisServices.countReasonsByRegionmotif(regionName);
    }


    @GetMapping("/tendancetest")
    public ResponseEntity<List<Object[]>> getTendanceByDateAndRegion(@RequestParam String regionName) {
        List<Object[]> data = analysisServices.getTendanceByDateAndRegion(regionName);
        return ResponseEntity.ok(data);
    }

//controller pour district

    //dbs et edta pour district

    @GetMapping("/districtbydbs")
    public List<Object[]> getDBSData(@RequestParam String districtName) {
        return analysisServices.getDISTRICTDBSData(districtName);
    }

    @GetMapping("/districtbyedta")
    public List<Object[]> getDISTRICTEDTAData(@RequestParam String districtName) {
        return analysisServices.getDISTRICTEDTAData(districtName);
    }




    @GetMapping("/districtpieresults")
    public List<Object[]> getAnalysisPieDistrictResults(@RequestParam String districtName) {
        return analysisServices.getAnalysisPieDistrictResults(districtName);
    }


 */































}
