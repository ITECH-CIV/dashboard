package org.itechciv.dashboard.impservice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.itechciv.dashboard.helper.CategoryAge;
import org.itechciv.dashboard.helper.Gender;
import org.itechciv.dashboard.helper.GenderOne;
import org.itechciv.dashboard.iservice.AnalysisService;
import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.repository.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
     }
    return null;
    }
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
    /* @Override
    public long getTotalTestsByYear(int year) {
        try{
            return analisysRepo.getTotalTestsByYear(year);
        }catch(Exception ex){
        }
    } */

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

    //genre test par district
   /*  @Override
    public List<Object[]> getAnalysisMaleForDistrictWithYear(Long districtId, int year) {
        String sex = "M"; // Sexe Feminin
        try{
            return analisysRepo.getAnalysisMaleForDistrictWithYear(districtId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }
 */
   /*  @Override
    public List<Object[]> getAnalysisFemaleForDistrictWithYear(Long districtId, int year) {
        try{
            String sex = "F"; // Sexe Feminin
            return analisysRepo.getAnalysisFemaleForDistrictWithYear(districtId, year, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    } */

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
     String sex = "M"; // Sexe masculin
     try{
         return analisysRepo.getAnalysisMaleByRegionWithYear(year, sex);
     }catch(Exception ex){
         ex.printStackTrace();
     }
    return null;
    }

 @Override
 public List<Object[]> getAnalysisFemaleByRegionWithYear(int year) {
     String sex = "F"; // Sexe Feminin
     try{
         return analisysRepo.getAnalysisFemaleByRegionWithYear(year, sex);
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
/* 
 @Override
 public List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(Long regionId, int year) {

    try{
     return analisysRepo.getTestedPatientByAgeMinusTwoForOneRegion(regionId, year);
    }catch(Exception ex){
     ex.printStackTrace();
     return Collections.emptyList();
    }
 } */
/* 
 @Override
 public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(Long regionId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeBetweenTwoAndNineForOneRegion(regionId, year);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
 }
 *//* 
 @Override
 public List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(Long regionId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(regionId, year); @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AN\n" + //
                  " \" JOIN Site s ON d.id = s.district.id \" +\n" + //
                  " \" JOIN Patient p ON s.id = p.site.id \" +\n" + //
                  " \" JOIN Analysis a ON p.id = a.patient.id \" +D 9 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

         return Collections.emptyList();
        }
 } */

/* 

 @Override
 public List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(Long regionId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(regionId, year);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
 } */
/* 
 @Override
 public List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(Long regionId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(regionId, year);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
 } */
/* 
 @Override
 public List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(Long regionId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(regionId, year);
        }catch(Exception ex){
         ex.printStackTrace();
         return Collections.emptyList();
        }
 } */

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
 public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(Long districtId, int year) {
     try{
         return analisysRepo.getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(districtId, year);
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
/* 
    @Override
    public List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(int year) {
        try{
            return analisysRepo.getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    } */

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
        try{
            return analisysRepo.getTestByAgeCategoryBetweenOneAndFourForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    }
/* 
    @Override
    public List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(Long regionId, int year) {
        try{
            return analisysRepo.getTestByAgeCategoryBetweenFiveAndNineForOneRegion(regionId, year);
           }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
           }
    } */

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
/* @Override
public List<Object[]> tendancetest2021() {

   try{
       return analisysRepo.tendancetest2021();
      }catch(Exception ex){
       ex.printStackTrace();
       return Collections.emptyList();
      }


}

 */
/* @Override
public List<Object[]> tendancetest2022() {

   try{
       return analisysRepo.tendancetest2022();
   }catch(Exception ex){
       ex.printStackTrace();
       return Collections.emptyList();
      }
}
 */

/* @Override
public List<Object[]> tendancetest2023() {

   try{
       return analisysRepo.tendancetest2023();

   }catch(Exception ex){
       ex.printStackTrace();
       return Collections.emptyList();
      }
} */

// test par tranche d age region
@Override
public List<Object[]> yourMethodName(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodName(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNameOne(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameOne(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNametwo(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNametwo(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNamethree(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNamethree(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> yourMethodNamefour(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNamefour(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}




@Override
public List<Object[]> yourMethodNameFive(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameFive(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> yourMethodNameSix(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameSix(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodNameseven(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameseven(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNameheight(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameheight(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNamenine(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNamenine(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNameten(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameten(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodNameeleven(Long regionId, int year) {

    try{
        return analisysRepo.yourMethodNameeleven(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

// test par tranche d age district
@Override
public List<Object[]> yourMethodDistrictName(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictName(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNameOne(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameOne(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodDistrictNametwo(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNametwo(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNamethree(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNamethree(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNamefour(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNamefour(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodDistrictNameFive(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameFive(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}
@Override
public List<Object[]> yourMethodDistrictNameSix(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameSix(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNameseven(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameseven(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }

}


@Override
public List<Object[]> yourMethodDistrictNameheight(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameheight(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodDistrictNamenine(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNamenine(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNameten(Long districtId, int year) {

    try{

        return analisysRepo.yourMethodDistrictNameten(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodDistrictNameeleven(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodDistrictNameeleven(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

// test par tranche d age site
@Override
public List<Object[]> yourMethodSiteName(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteName(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNameOne(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameOne(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNametwo(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNametwo(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNamethree(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNamethree(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodSiteNamefour(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNamefour(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNameFive(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameFive(siteId, year);
        
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> yourMethodSiteNameSix(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameSix(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNameseven(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameseven(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodSiteNameheight(Long districtId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameheight(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodSiteNamenine(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNamenine(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> yourMethodSiteNameten(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameten(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> yourMethodSiteNameeleven(Long siteId, int year) {

    try{
        return analisysRepo.yourMethodSiteNameeleven(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

//pour toute les regions
@Override
public List<Object[]>MethodRegion(int year) {

    try{
        return analisysRepo.MethodRegion(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]>MethodRegionOne(int year) {

    try{
        return analisysRepo.MethodRegionOne(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]>MethodRegionThree(int year) {

    try{
        return analisysRepo.MethodRegionThree(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]>MethodRegionfour(int year) {

    try{
        return analisysRepo.MethodRegionfour(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]>MethodRegionfive(int year) {

    try{
        return analisysRepo.MethodRegionfive(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]>MethodRegionsix(int year) {

    try{
        return analisysRepo.MethodRegionsix(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]>MethodRegionseven(int year) {

    try{
        return analisysRepo.MethodRegionseven(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]>MethodRegionheight(int year) {

    try{
        return analisysRepo.MethodRegionheight(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]>MethodRegionnine(int year) {

    try{
        return analisysRepo.MethodRegionnine(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]>MethodRegionten(int year) {

    try{
        return analisysRepo.MethodRegionten(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]>MethodRegioneleven(int year) {

    try{
        return analisysRepo.MethodRegioneleven(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]>MethodRegiontwelve(int year) {

    try{
        return analisysRepo.MethodRegiontwelve(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


//par test exemple
@Override
public List<Object[]> findByYearByRegiontest(int year, Long regionId) {

    try{
        return analisysRepo.findByYearByRegiontest(year, regionId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getAnalysisPatientMaleForDistrictWithYear(Long districtId, int year) {
    try{
        String sex = "M"; // Sexe Feminin
        return analisysRepo.getAnalysisPatientMaleForDistrictWithYear(districtId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }

}

@Override
public List<Object[]> getAnalysisPatientFemaleForDistrictWithYear(Long districtId, int year) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getAnalysisPatientFemaleForDistrictWithYear(districtId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getAnalysisPatientMaleForRegionWithYearM(Long regionId, int year) {
    try{
        String sex = "M"; // Sexe Feminin
    return analisysRepo.getAnalysisPatientMaleForRegionWithYearM(regionId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getAnalysisPatientFemaleForRegionWithYearF(Long regionId, int year) {
    try{
        String sex = "F"; // Sexe Feminin
    return analisysRepo.getAnalysisPatientFemaleForRegionWithYearF(regionId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getAnalysisPatientMaleForSiteWithYearSiteM(Long siteId, int year) {
    try{
        String sex = "M"; // Sexe Feminin
        return analisysRepo.getAnalysisPatientMaleForSiteWithYearSiteM(siteId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getAnalysisPatientFemaleForSiteWithYearsiteF(Long siteId, int year) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getAnalysisPatientFemaleForSiteWithYearsiteF(siteId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientForAllRegion(int year) {
    try{
        return analisysRepo.getPatientForAllRegion(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientForDistrict(Long districtId, int year) {
    try{
        return analisysRepo.getPatientForDistrict(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientForDistrictWithSite(Long districtId, int year) {

    try{
        return analisysRepo.getPatientForDistrictWithSite(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneSite(Long siteId, int year) {
    try{
        return analisysRepo.getTestedPatientByAgeBetweenTwoAndNineForOneSite(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestBySiteForOneRegion(Long regionId, int year) {

    try{
        return analisysRepo.getTestBySiteForOneRegion(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientBySiteForOneRegion(Long regionId, int year) {
    try{
        return analisysRepo.getPatientBySiteForOneRegion(regionId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getTestByPartner(int year) {
    try{
        return analisysRepo.getTestByPartner(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientByPartner(int year) {
    try{
        return analisysRepo.getPatientByPartner(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getestByspecimenEDTAPlasma(int partnerId) {
    try{
        return analisysRepo.getestByspecimenEDTAPlasma(partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getestByspecimenDBS(int partnerId) {
    try{
        return analisysRepo.getestByspecimenDBS(partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getestByspecimenPSC(int partnerId) {
    try{
        return analisysRepo.getestByspecimenPSC(partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district7(Long districtId, int year) {
    try{
        return analisysRepo.district7(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site8(Long siteId, int year) {
    try{
        return analisysRepo.site8(siteId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestWithDetailsByPartner(int year, Long partnerId) {
    try{
        return analisysRepo.getTestWithDetailsByPartner(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientWithDetailsByPartner(int year, Long partnerId) {
    try{
        return analisysRepo.getPatientWithDetailsByPartner(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> districtSeven(Long districtId, int year) {
    try{
        return analisysRepo.districtSeven(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtEleven(Long districtId, int year) {
    try{
        return analisysRepo.districtEleven(districtId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> testOne(int year) {
    try{
        return analisysRepo.testOne(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> testTwo(int year) {
    try{
        return analisysRepo.testTwo(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> testThree(int year) {
    try{
        return analisysRepo.testThree(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> testFour(int year) {
    try{
        return analisysRepo.testFour(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> testFive(int year) {
    try{
        return analisysRepo.testFive(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> testSix(int year) {
    try{
        return analisysRepo.testSix(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> patientOne(int year) {
    try{
        return analisysRepo.patientOne(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}


@Override
public List<Object[]> patientTwo(int year) {
    try{
        return analisysRepo.patientTwo(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}
 


@Override
public List<Object[]> patientThree(int year) {
    try{
        return analisysRepo.patientThree(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}
 


@Override
public List<Object[]> patientFour(int year) {
    try{
        return analisysRepo.patientFour(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}
 

@Override
public List<Object[]> patientFive(int year) {
    try{
        return analisysRepo.patientFive(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}
 


@Override
public List<Object[]> patientSix(int year) {
    try{
        return analisysRepo.patientSix(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

@Override
public List<Object[]> getTestForSpecificPartner(int year, Long partnerId) {
    try{
        return analisysRepo.getTestForSpecificPartner(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}


@Override
public List<Object[]> getPatientForSpecificPartner(int year, Long partnerId) {
    try{
        return analisysRepo.getPatientForSpecificPartner(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

  
    //genre masculin
    @Override
    public List<Object[]> getPatientForOnePartnerByMale(int year, Long partnerId) {
        String sex = "M"; 
        try{
            return analisysRepo.getPatientForOnePartnerByMale(year, partnerId, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    }

    @Override
    public List<Object[]> getPatientForOnePartnerByFemale(int year, Long partnerId) {
        String sex = "F"; 
        try{
            return analisysRepo.getPatientForOnePartnerByFemale(year, partnerId, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    } 

    @Override
    public List<Object[]> getTestForOnePartnerByMale(int year, Long partnerId) {
        String sex = "M"; 
        try{
            return analisysRepo.getTestForOnePartnerByMale(year, partnerId, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    } 

    @Override
    public List<Object[]> getTestForOnePartnerByFemale(int year, Long partnerId) {
        String sex = "F"; 
        try{
            return analisysRepo.getTestForOnePartnerByFemale(year, partnerId, sex);
        }catch(Exception ex){
            ex.printStackTrace();
            return Collections.emptyList(); 
        }
    } 


@Override
public List<Object[]> motifVlreasonByOnePartner(int year, Long partnerId) {
    try{
        return analisysRepo.motifVlreasonByOnePartner(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}


@Override
public List<Object[]> getTestBySiteForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getTestBySiteForOnePartner(partnerId,year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

@Override
public List<Object[]> getPatientBySiteForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getPatientBySiteForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
} 


@Override
public List<Object[]> geTestByCategoryCDCForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.geTestByCategoryCDCForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

@Override
public List<Object[]> geTestByCategoryNationalForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.geTestByCategoryNationalForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}


@Override
public List<Object[]> getPatientByCategoryCdciForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getPatientByCategoryCdciForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

@Override
public List<Object[]> getPatientByCategoryNationalForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getPatientByCategoryNationalForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}

@Override
public List<Object[]> getTestByRegimenForAllPartner(int year) {
    try{
        return analisysRepo.getTestByRegimenForAllPartner(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }    
}


@Override
public List<Object[]> getPatientByRegimenForAllPartner(int year) {
    try{
        return analisysRepo.getPatientByRegimenForAllPartner(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }   
}

@Override
public List<Object[]> getTestByRegimenForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getTestByRegimenForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }   
}

@Override
public List<Object[]> getPatientByRegimenForOnePartner(Long partnerId, int year) {
    try{
        return analisysRepo.getPatientByRegimenForOnePartner(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestBySpecimenDBSForOneRegimen(Long regimenId) {
    try{
        return analisysRepo.getestBySpecimenDBSForOneRegimen(regimenId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getestBySpecimenPSCForOneRegimen(Long regimenId) {
    try{
        return analisysRepo.getestBySpecimenPSCForOneRegimen(regimenId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestBySpecimenEdtaPlasmaForOneRegimen(Long regimenId) {
    try{
        return analisysRepo.getestBySpecimenEdtaPlasmaForOneRegimen(regimenId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestForOneRegimen(Long regimenId, int year) {
    try{
        return analisysRepo.getTestForOneRegimen(regimenId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getTestByCDCMaleForOneRegimen(Long regimenId, int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getTestByCDCMaleForOneRegimen(regimenId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByCdcFemaleForOneRegimen(Long regimenId, int year) {
    try{
        String sex = "F"; // Sexe Féminin
        return analisysRepo.getTestByCdcFemaleForOneRegimen(regimenId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientByCDCMaleForOneRegimen(Long regimenId, int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getPatientByCDCMaleForOneRegimen(regimenId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getPatientByCDCFemaleForOneRegimen(Long regimenId, int year) {
    try{
        String sex = "F"; // Sexe Féminin
        return analisysRepo.getPatientByCDCFemaleForOneRegimen(regimenId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestBySpecimenDBSForRegimenAndPartnerOne(Long regimenId, Long partnerId) {
    try{
        return analisysRepo.getestBySpecimenDBSForRegimenAndPartnerOne(regimenId, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestBySpecimenPSCForRegimenAndPartnerOne(Long regimenId, Long partnerId) {
    try{
        return analisysRepo.getestBySpecimenPSCForRegimenAndPartnerOne(regimenId, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(Long regimenId, Long partnerId) {
    try{
        return analisysRepo.getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(regimenId, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestForRegimenAndPartnerOne(Long regimenId, Long partnerId, int year) {
    try{
        return analisysRepo.getTestForRegimenAndPartnerOne(regimenId, partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByCDCMaleForRegimenAndPartnerOne(Long regimenId, Long partnerId, int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getTestByCDCMaleForRegimenAndPartnerOne(regimenId, partnerId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByCDCFemaleForRegimenAndPartnerOne(Long regimenId, Long partnerId, int year) {
    try{
        String sex = "F"; // Sexe Féminin
        return analisysRepo.getTestByCDCFemaleForRegimenAndPartnerOne(regimenId, partnerId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientByCDCMaleForRegimenAndPartnerOne(Long regimenId, Long partnerId, int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getPatientByCDCMaleForRegimenAndPartnerOne(regimenId, partnerId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientByCDCFemaleForRegimenAndParterOne(Long regimenId, Long partnerId, int year) {
    try{
        String sex = "F"; // Sexe Féminin
        return analisysRepo.getPatientByCDCFemaleForRegimenAndParterOne(regimenId, partnerId, year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestAndPatientResultForPartnerAndRegion(int year) {
    try{
        return analisysRepo.getTestAndPatientResultForPartnerAndRegion(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getTestAndPatientResultForOnePartnerAndRegion(int year, Long partnerId) {
    try{
        return analisysRepo.getTestAndPatientResultForOnePartnerAndRegion(year, partnerId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestAndPatientResultForAllLab(int year) {
    try{
        return analisysRepo.getTestAndPatientResultForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestAndPatientResultForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getTestAndPatientResultForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestAndPatientBySpecimenDBSForAllLab(int year) {
    try{
        return analisysRepo.getestAndPatientBySpecimenDBSForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getestAndPatientBySpecimenPSCForAllLab(int year) {
    try{
        return analisysRepo.getestAndPatientBySpecimenPSCForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForAllLab(int year) {
    try{
        return analisysRepo.getestAndPatientBySpecimenEdtaPlasmaForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getestAndPatientBySpecimenDBSForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getestAndPatientBySpecimenDBSForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getestAndPatientBySpecimenPSCForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getestAndPatientBySpecimenPSCForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}


@Override
public List<Object[]> getestAndPatientBySpecimenEdtaPlasmaForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getestAndPatientBySpecimenEdtaPlasmaForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestForAllLab(int year) {
    try{
        return analisysRepo.getTestForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getTestForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getPatientForAllLab(int year) {
    try{
        return analisysRepo.getPatientForAllLab(year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}


@Override
public List<Object[]> getPatientForOneLab(int year, Long labId) {
    try{
        return analisysRepo.getPatientForOneLab(year, labId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getPatientByCDCMaleAndPartnerAll(int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getPatientByCDCMaleAndPartnerAll(year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientByCDCFemaleAndPartnerAll(int year) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getPatientByCDCFemaleAndPartnerAll(year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getTestByCDCMaleAndPartnerAll(int year) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getTestByCDCMaleAndPartnerAll(year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getTestByCDCFemaleAndPartnerAll(int year) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getTestByCDCFemaleAndPartnerAll(year, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientByCDCMaleAndPartnerOne(int year, Long partnerId) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getPatientByCDCMaleAndPartnerOne(year, partnerId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getPatientByCDCFemaleAndPartnerOne(int year, Long partnerId) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getPatientByCDCFemaleAndPartnerOne(year, partnerId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByCDCMaleAndPartnerOne(int year, Long partnerId) {
    try{
        String sex = "M"; // Sexe Feminin
        return analisysRepo.getTestByCDCMaleAndPartnerOne(year, partnerId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByCDCFemaleAndPartnerOne(int year, Long partnerId) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getTestByCDCFemaleAndPartnerOne(year, partnerId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getTestBySpecimen(Long ageCategoryId) {
    try{
        return analisysRepo.getTestBySpecimen(ageCategoryId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getTestByAgeCategoryCdc(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getTestByAgeCategoryCdc(year, ageCategoryId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientMaleByCdc(int year, Long ageCategoryId) {
    try{
        String sex = "M"; // Sexe Masculin
        return analisysRepo.getPatientMaleByCdc(year, ageCategoryId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}

@Override
public List<Object[]> getPatientFemaleByCdc(int year, Long ageCategoryId) {
    try{
        String sex = "F"; // Sexe Feminin
        return analisysRepo.getPatientFemaleByCdc(year, ageCategoryId, sex);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}


@Override
public List<Object[]> getPatientNothingByCdc(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientNothingByCdc(year, ageCategoryId);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}
/************************************************************DEBUT *****************************************************************/

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
public List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(Long regionId,int year) {
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
        return analisysRepo.getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(regionId,year);
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
public List<Object[]> getTestedPatientByAgeCategoryMinusTwoForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryMinusTwoForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}


@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenOneAndFourForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenFiveAndNineForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenTenAndFourteenForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       } 
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForAllRegion(int year) {
    try{
        return analisysRepo.getTestedPatientByAgeCategoryGreaterThanFiftyForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district1(Long districtId, int year) {
    try{
        return analisysRepo.district1(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district2(Long districtId, int year) {
    try{
        return analisysRepo.district2(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district3(Long districtId, int year) {
    try{
        return analisysRepo.district3(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district4(Long districtId, int year) {
    try{
        return analisysRepo.district4(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district5(Long districtId, int year) {
    try{
        return analisysRepo.district5(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district6(Long districtId, int year) {
    try{
        return analisysRepo.district6(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> district8(Long districtId, int year) {
    try{
        return analisysRepo.district8(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district9(Long districtId, int year) {
    try{
        return analisysRepo.district9(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district10(Long districtId, int year) {
    try{
        return analisysRepo.district10(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> district11(Long districtId, int year) {
    try{
        return analisysRepo.district11(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> district12(Long districtId, int year) {
    try{
        return analisysRepo.district12(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site1(Long siteId, int year) {
    try{
        return analisysRepo.site1(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site2(Long siteId, int year) {
    try{
        return analisysRepo.site2(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site3(Long siteId, int year) {
    try{
        return analisysRepo.site3(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site4(Long siteId, int year) {
    try{
        return analisysRepo.site4(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site5(Long siteId, int year) {
    try{
        return analisysRepo.site5(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site6(Long siteId, int year) {
    try{
        return analisysRepo.site6(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site7(Long siteId, int year) {
    try{
        return analisysRepo.site7(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site9(Long siteId, int year) {
    try{
        return analisysRepo.site9(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> site10(Long siteId, int year) {
    try{
        return analisysRepo.site9(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site12(Long siteId, int year) {
    try{
        return analisysRepo.site12(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> districtEight(Long districtId, int year) {
    try{
        return analisysRepo.districtEight(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtNine(Long districtId, int year) {
    try{
        return analisysRepo.districtNine(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtTen(Long districtId, int year) {
    try{
        return analisysRepo.districtTen(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> districtTwelve(Long districtId, int year) {
    try{
        return analisysRepo.districtTwelve(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteSeven(Long siteId, int year) {
    try{
        return analisysRepo.siteSeven(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> site11(Long siteId, int year) {
    try{
        return analisysRepo.site11(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
    }
}


@Override
public List<Object[]> siteEight(Long siteId, int year) {
    try{
        return analisysRepo.siteEight(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteNine(Long siteId, int year) {
    try{
        return analisysRepo.siteNine(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteTen(Long siteId, int year) {
    try{
        return analisysRepo.siteTen(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> siteEleven(Long siteId, int year) {
    try{
        return analisysRepo.siteEleven(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteTwelve(Long siteId, int year) {
    try{
        return analisysRepo.siteTwelve(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtOne(Long districtId, int year) {
    try{
        return analisysRepo.districtOne(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> districtTwo(Long districtId, int year) {
    try{
        return analisysRepo.districtTwo(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtThree(Long districtId, int year) {
    try{
        return analisysRepo.districtThree(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtFour(Long districtId, int year) {
    try{
        return analisysRepo.districtThree(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtFive(Long districtId, int year) {
    try{
        return analisysRepo.districtFive(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> districtSix(Long districtId, int year) {
    try{
        return analisysRepo.districtSix(districtId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteOne(Long siteId, int year) {
    try{
        return analisysRepo.siteOne(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteTwo(Long siteId, int year) {
    try{
        return analisysRepo.siteTwo(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteThree(Long siteId, int year) {
    try{
        return analisysRepo.siteThree(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteFour(Long siteId, int year) {
    try{
        return analisysRepo.siteFour(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteFive(Long siteId, int year) {
    try{
        return analisysRepo.siteFive(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> siteSix(Long siteId, int year) {
    try{
        return analisysRepo.siteSix(siteId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> patientOnen(int year) {
    try{
        return analisysRepo.patientOnen(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> patientTwon(int year) {
    try{
        return analisysRepo.patientTwon(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> patientThreen(int year) {
    try{
        return analisysRepo.patientThreen(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> patientFourn(int year) {
    try{
        return analisysRepo.patientFourn(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> patientFiven(int year) {
    try{
        return analisysRepo.patientFiven(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
    }

@Override
public List<Object[]> patientSixn(int year) {
     try{
        return analisysRepo.patientSixn(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getestByspecimenEDTAPlasmapartenaire(int partnerId) {
    try{
        return analisysRepo.getestByspecimenEDTAPlasmapartenaire(partnerId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getestByspecimenDBSpartenaire(int partnerId) {
    try{
        return analisysRepo.getestByspecimenDBSpartenaire(partnerId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getestByspecimenPSCpartenaire(int partnerId) {
    try{
        return analisysRepo.getestByspecimenPSCpartenaire(partnerId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestsAndPatientsByRegionliste(int year) {
    try{
        return analisysRepo.getTestsAndPatientsByRegionliste(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getTestsAndPatientsByDistrictliste(int year) {
    try{
        return analisysRepo.getTestsAndPatientsByDistrictliste(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestsAndPatientsBySitePartnerliste(int year) {
    try{
        return analisysRepo.getTestsAndPatientsBySitePartnerliste(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestsAndPatientsBySitechaquepartenaireliste(int year, Long partnerId) {
    try{
        return analisysRepo.getTestsAndPatientsBySitechaquepartenaireliste(year, partnerId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
    }

     
@Override
public List<Object[]> getTestByRegimenForAllPartnerone(int year) {
    try{
        return analisysRepo.getTestByRegimenForAllPartnerone(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientByRegimenForAllPartnertwo(int year) {
    try{
        return analisysRepo.getPatientByRegimenForAllPartnertwo(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getTestByRegimenForOnePartnerOne(Long partnerId, int year) {
    try{
        return analisysRepo.getTestByRegimenForOnePartnerOne(partnerId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByRegimenForOnePartnerOne(Long partnerId, int year) {
    try{
        return analisysRepo.getPatientByRegimenForOnePartnerOne(partnerId, year);
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
public List<Object[]> getAnalysisPatientMaleByRegionWithSpecificYear(int year) {
    String sex = "M";
    try{
        return analisysRepo.getAnalysisPatientMaleByRegionWithSpecificYear(year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}






@Override
public List<Object[]> getViralLoadMaleByRegion(int year) {
    String sex = "M"; // Sexe masculin
    try{
        return analisysRepo.getViralLoadMaleByRegion(year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadFemaleByRegion(int year) {
    String sex = "F"; // Sexe Feminin
    try{
        return analisysRepo.getViralLoadFemaleByRegion(year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadNothingByRegion(int year) {
    try{
        return analisysRepo.getViralLoadNothingByRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientMinusTenForAllRegion(int year) {
    try{
        return analisysRepo.getPatientMinusTenForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientBetweenTenAndFourteenForAllRegion(int year) {
    try{
        return analisysRepo.getPatientBetweenTenAndFourteenForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientGreaterThanTwentyForAllRegion(int year) {
    try{
        return analisysRepo.getPatientGreaterThanTwentyForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> motifVlreasonForAllRegion(int year) {
    try{
        return analisysRepo.motifVlreasonForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadMaleOtherByRegion(int year) {
    String sex = "M"; // Sexe masculin
    try{
        return analisysRepo.getViralLoadMaleOtherByRegion(year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadFemaleOtherByRegion(int year) {
    String sex = "F"; // Sexe Féminin
    try{
        return analisysRepo.getViralLoadMaleOtherByRegion(year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getViralLoadNothingOtherByRegion(int year) {
    try{
        return analisysRepo.getViralLoadNothingOtherByRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientByAgeNationalForAllRegion(int year) {
    try{
        return analisysRepo.getPatientByAgeNationalForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getNotDeletionByRegion(int year) {
    try{
        return analisysRepo.getNotDeletionByRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getNotDeletionByDistrict(int year) {
    try{
        return analisysRepo.getNotDeletionByDistrict(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getNotDeletionBySite(int year) {
    try{
        return analisysRepo.getNotDeletionBySite(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getNotDeletionByPartner(int year) {
    try{
        return analisysRepo.getNotDeletionByPartner(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadDeletionForAllRegion(int year) {
    try{
        return analisysRepo.getViralLoadDeletionForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientNothingAgeForAllRegion(int year) {
    try{
        return analisysRepo.getPatientNothingAgeForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getViralLoadMaleForOneRegion(Long regionId, int year) {
    String sex = "M"; // Sexe Masculin
    try{
        return analisysRepo.getViralLoadMaleForOneRegion(regionId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getViralLoadFemaleForOneRegion(Long regionId, int year) {
    String sex = "F"; // Sexe Feminin
    try{
        return analisysRepo.getViralLoadFemaleForOneRegion(regionId, year, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getViralLoadNothingForOneRegion(Long regionId, int year) {
    try{
        return analisysRepo.getViralLoadNothingForOneRegion(regionId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientMinusTenForOneRegion(Long regionId, int year) {
    try{
        return analisysRepo.getPatientMinusTenForOneRegion(regionId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByAgeNationalForOneRegion(int year, Long regionId) {
    try{
        return analisysRepo.getPatientByAgeNationalForOneRegion(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getViralLoadByGenderForAllRegion(int year) {
    String sexM = "M"; // Sexe Masculin
    String sexF = "F"; // Sexe Feminin
    try{
        return analisysRepo.getViralLoadByGenderForAllRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> getNotDeletionForOneRegion(int year, Long regionId) {
    try{
        return analisysRepo.getNotDeletionForOneRegion(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getNotDeletionForOneDistrict(int year, Long regionId) {
    try{
        return analisysRepo.getNotDeletionForOneDistrict(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getNotDeletionForOneSite(int year, Long regionId) {
    try{
        return analisysRepo.getNotDeletionForOneSite(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getNotDeletionForOnePartner(int year, Long regionId) {
    try{
        return analisysRepo.getNotDeletionForOnePartner(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByRegionForOneRegimen(Long regimenId, int year) {
    try{
        return analisysRepo.getPatientByRegionForOneRegimen(regimenId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
} 


@Override
public List<Object[]> getPatientByPartnerForOneRegimen(Long regimenId, int year) {
    try{
        return analisysRepo.getPatientByPartnerForOneRegimen(regimenId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByDistrictForOneRegimen(Long regimenId, int year) {
    try{
        return analisysRepo.getPatientByDistrictForOneRegimen(regimenId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientBySiteForOneRegimen(Long regimenId, int year) {
    try{
        return analisysRepo.getPatientBySiteForOneRegimen(regimenId, year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientMaleByAgeCdcForOnRegimen(int year, Long regimenId) {
    String sex = "M"; // Sexe Masculin
    try{
        return analisysRepo.getPatientMaleByAgeCdcForOnRegimen(year, regimenId, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> getPatientFemaleByAgeCdcForOnRegimen(int year, Long regimenId) {
    String sex = "F"; // Sexe Feminin
    try{
        return analisysRepo.getPatientFemaleByAgeCdcForOnRegimen(year, regimenId, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
} 


@Override
public List<Object[]> getTestMaleByAgeCdcForOneRegimen(int year, Long regimenId) {
    String sex = "M"; // Sexe Masculin
    try{
        return analisysRepo.getTestMaleByAgeCdcForOneRegimen(year, regimenId, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> getTestFemaleByAgeCdcForOneRegimen(int year, Long regimenId) {
    String sex = "F"; // Sexe Feminin
    try{
        return analisysRepo.getTestFemaleByAgeCdcForOneRegimen(year, regimenId, sex);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByAgeCdcForRegion(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientByAgeCdcForRegion(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}



@Override
public List<Object[]> getPatientByAgeCdcForPartner(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientByAgeCdcForPartner(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}




@Override
public List<Object[]> getPatientByAgeCdcForDistrict(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientByAgeCdcForDistrict(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientByAgeCdcForSite(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientByAgeCdcForSite(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Gender> getPatientByGenderForAllRegion(int year) {

List<Gender> response = new ArrayList<Gender>();
List<Object[]> results;

    try{
        results =  analisysRepo.getPatientByGenderForAllRegion(year);

        for(Object[] o: results){
            Gender gender = new Gender();

            if(o[0].equals("F")){
				gender.setLabel(o[0].toString());
				gender.setNotDeletionTotal(Integer.parseInt(o[1].toString()));
				gender.setDeletionTotal(Integer.parseInt(o[2].toString()));
                gender.setDeletionPercentage(Double.parseDouble(o[3].toString()));
            }else if(o[0].equals("M")){
                gender.setLabel(o[0].toString());
				gender.setNotDeletionTotal(Integer.parseInt(o[1].toString()));
				gender.setDeletionTotal(Integer.parseInt(o[2].toString()));
                gender.setDeletionPercentage(Double.parseDouble(o[3].toString()));  
            }else{
                gender.setLabel("Aucune donnee");
				gender.setNotDeletionTotal(Integer.parseInt(o[1].toString()));
				gender.setDeletionTotal(Integer.parseInt(o[2].toString()));
                gender.setDeletionPercentage(Double.parseDouble(o[3].toString())); 
            }
          response.add(gender);
        }
        return response;
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
} 


@Override
public List<GenderOne> getPatientByGenderForOneRegion(int year, Long regionId ) {

List<GenderOne> response = new ArrayList<GenderOne>();
List<Object[]> results;

    try{
        results =  analisysRepo.getPatientByGenderForOneRegion(year, regionId);

        for(Object[] o: results){
            GenderOne genderOne = new GenderOne();

            if(o[1].equals("F")){
                genderOne.setRegionName(o[0].toString());
				genderOne.setLabel(o[1].toString());
				genderOne.setNotDeletionTotal(Integer.parseInt(o[2].toString()));
				genderOne.setDeletionTotal(Integer.parseInt(o[3].toString()));
                genderOne.setDeletionPercentage(Double.parseDouble(o[4].toString()));
            }else if(o[1].equals("M")){
                genderOne.setRegionName(o[0].toString());
                genderOne.setLabel(o[1].toString());
				genderOne.setNotDeletionTotal(Integer.parseInt(o[2].toString()));
				genderOne.setDeletionTotal(Integer.parseInt(o[3].toString()));
                genderOne.setDeletionPercentage(Double.parseDouble(o[4].toString()));  
            }else{
                genderOne.setRegionName(o[0].toString());
                genderOne.setLabel("Aucune donnee");
				genderOne.setNotDeletionTotal(Integer.parseInt(o[2].toString()));
				genderOne.setDeletionTotal(Integer.parseInt(o[3].toString()));
                genderOne.setDeletionPercentage(Double.parseDouble(o[4].toString())); 
            }
          response.add(genderOne);
        }
        return response;
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> motifVlreasonForEntireRegion(int year) {
    try{
        return analisysRepo.motifVlreasonForEntireRegion(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> motifVlreasonForOneRegion(int year, Long regionId) {
    try{
        return analisysRepo.motifVlreasonForOneRegion(year, regionId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientForAllNationalAgeCategory(int year) {
    try{
        return analisysRepo.getPatientForAllNationalAgeCategory(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientForAllCDCAgeCategory(int year) {
    try{
        return analisysRepo.getPatientForAllCDCAgeCategory(year);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}


@Override
public List<Object[]> getPatientForOneNationalAgeCategory(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientForOneNationalAgeCategory(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}

@Override
public List<Object[]> getPatientForOneCDCAgeCategory(int year, Long ageCategoryId) {
    try{
        return analisysRepo.getPatientForOneCDCAgeCategory(year, ageCategoryId);
    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }
}













/* 
@Override
public List<Object[]> tendancetest2021() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'tendancetest2021'");
} */
/* 
@Override
public List<Object[]> tendancetest2022() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'tendancetest2022'");
} */
/* 
@Override
public List<Object[]> tendancetest2023() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'tendancetest2023'");
} */









}

/**************************************************************FIN *****************************************************************/

/* @Override
public List<Object[]> getTestByCategoryAgeMinus2(Long partnerId, int year) {
    try{
        return analisysRepo.getTestByCategoryAgeMinus2(partnerId,year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
} */
/* 
@Override
public List<Object[]> getTestByCategoryAgeBetweenTwoAndNine(Long partnerId, int year) {
    try{
        return analisysRepo.getTestByCategoryAgeBetweenTwoAndNine(partnerId,year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}
  */

  /* 
@Override
public List<Object[]> getTestBySpecificCategoryAgeBetweenTenAndFourteen(Long partnerId, int year) {
    try{
        return analisysRepo.getTestBySpecificCategoryAgeBetweenTenAndFourteen(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}  */

/* 
@Override
public List<Object[]> getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(Long partnerId, int year) {
    try{
        return analisysRepo.getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}  */
/* 
@Override
public List<Object[]> getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(Long partnerId, int year) {
    try{
        return analisysRepo.getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
}
 */
/* 
 @Override
public List<Object[]> getTestBySpecificCategoryAgeGreaterThanTwentyFive(Long partnerId, int year) {
    try{
        return analisysRepo.getTestBySpecificCategoryAgeGreaterThanTwentyFive(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
} 
 */
//Méthode optimisee
/* 
@Override
public List<Object[]> testCategorieOptimize(Long partnerId, int year) {
    try{
        return analisysRepo.testCategorieOptimize(partnerId, year);

    }catch(Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
       }  
} */