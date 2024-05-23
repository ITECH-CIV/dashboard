package org.itechciv.dashboard.repository;

import java.util.List;

import org.itechciv.dashboard.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
	
	@Query(value = "select a.*" + 
			" from dashboard.analysis a" + 
			" where a.lab_no =?1", nativeQuery = true)	
	Analysis findAnalysisByCode(String code); 


	@Query(value = "SELECT COUNT(a.id) as total_analyse, r.id, r.name, p.gender " + 
			"from dashboard.region r " + 
			"left join dashboard.district d ON r.id = d.region_id " +
			"left join dashboard.site s ON d.id = s.district_id " +
			"left join dashboard.patient p ON s.id = p.site_id " +
			"left join dashboard.analysis a ON p.id = a.patient_id " +
			"left join dashboard.test t on t.id = a.test_id " +
			"where r.id  =?1 " + 
			"group by r.id, r.name, p.gender " +
			"order by r.name", nativeQuery = true)	
	List<Object[]> totalAnalysisByGenderForRegions(Long regionId); 


	/* @Query(value = " SELECT " +
	        " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) as NBRE_LL "
			COUNT(a.id) as total_analyse, r.id, r.name, p.gender " + 
			"from dashboard.region r " + 
			"left join dashboard.district d ON r.id = d.region_id " +
			"left join dashboard.site s ON d.id = s.district_id " +
			"left join dashboard.patient p ON s.id = p.site_id " +
			"left join dashboard.analysis a ON p.id = a.patient_id " +
			"left join dashboard.test t on t.id = a.test_id " +
			"where r.id  =?1 " + 
			"group by r.id, r.name, p.gender " +
			"order by r.name", nativeQuery = true)	
	List<Object[]> totalAnalysisByGenderForRegion(Long regionId); 


 */

    //premier graphe resumé pour EDTA par region
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.region.id = :regionName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'Tube EDTA - Violet' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenEDTAData(@Param("regionName") int regionName);

    //premier graphe resumé pour DBS par region
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.region.id = :regionName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'DBS' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenDBSData(@Param("regionName") int regionName);

    //premier graphe resumé pour DBS par region
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.region.id = :regionName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'PSC' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenPSCData(@Param("regionName") int regionName);

   //pour district DBS, EDTA, PSC
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.district.id = :districtName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'PSC' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenDISTRICTPSC(@Param("districtName") int districtName);

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.district.id = :districtName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'DBS' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenDISTRICTDBS(@Param("districtName") int districtName);

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.district.id = :districtName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'Tube EDTA - Violet' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenDISTRICTEDTA(@Param("districtName") int districtName);

// PAR SPECIMEN POUR SITE DBS EDTA PSC
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.site.id = :siteName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'Tube EDTA - Violet' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenSITEEDTA(@Param("siteName") int siteName);

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.site.id = :siteName " +

            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'DBS' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenSITEDBS(@Param("siteName") int siteName);

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE dashboard.site.id = :siteName " +
            "AND dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'PSC' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisBySpecimenSITEPSC(@Param("siteName") int siteName);


    //REQUETE POUR TOUTE LES REGIONS
    //par specimen dbs, edta, PSC
    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE  dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'Tube EDTA - Violet' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenGeneralEDTAData();

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE  dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'DBS' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenGeneralDBSData();

    @Query(value = "SELECT TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY') AS date, " +
            "dashboard.sample_type.label, " +
            "COUNT(dashboard.test.id) AS total_tests " +
            "FROM dashboard.region " +
            "JOIN dashboard.district ON dashboard.region.id = dashboard.district.region_id " +
            "JOIN dashboard.site ON dashboard.district.id = dashboard.site.district_id " +
            "JOIN dashboard.patient ON dashboard.site.id = dashboard.patient.site_id " +
            "JOIN dashboard.analysis ON dashboard.patient.id = dashboard.analysis.patient_id " +
            "JOIN dashboard.test ON dashboard.analysis.test_id = dashboard.test.id " +


            "JOIN dashboard.regimen ON dashboard.analysis.regimen_id = dashboard.regimen.id " +
            "JOIN dashboard.vl_reason ON dashboard.analysis.vl_reason_id = dashboard.vl_reason.id " +
            "JOIN dashboard.sample_type ON dashboard.analysis.sample_type_id = dashboard.sample_type.id " +

            "WHERE  dashboard.analysis.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
            "AND dashboard.sample_type.label = 'PSC' " +
            "GROUP BY dashboard.analysis.drcpt, TO_CHAR(dashboard.analysis.drcpt, 'Mon-YYYY'), dashboard.sample_type.label " +
            "ORDER BY dashboard.analysis.drcpt ASC;",
            nativeQuery = true)
    List<Object[]> getAnalysisByspecimenGeneralPSCData();

    //charge virale test pour chaque region
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS negative_one_count, " +
            " SUM(1) AS total_count, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS negative_one_percentage, " +
            " ROUND(SUM(1) * 100.0 / SUM(1), 2) AS total_percentage, " +
            " SUM(1) AS total " + // Ajoutez cette ligne pour obtenir le total en chiffres
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE r.id = :regionId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id")
    List<Object[]> findByYear(@Param("year") int year, @Param("regionId") Long regionId);

    //charge virale test pour toutes les regions
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS negative_one_count, " +
            " SUM(1) AS total_count, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS negative_one_percentage, " +
            " ROUND(SUM(1) * 100.0 / SUM(1), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE EXTRACT(YEAR FROM a.drcpt) = :year")
    List<Object[]> findByYearReg(@Param("year") int year);

    //charge virale test pour chaque district
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS negative_one_count, " +
            " SUM(1) AS total_count, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS negative_one_percentage, " +
            " ROUND(SUM(1) * 100.0 / SUM(1), 2) AS total_percentage, " +
            " SUM(1) AS total " + // Ajoutez cette ligne pour obtenir le total en chiffres
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE d.id = :districtId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id")
    List<Object[]> findByYearDistrict(@Param("year") int year, @Param("districtId") Long districtId);

    //charge virale test pour chaque site
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS negative_one_count, " +
            " SUM(1) AS total_count, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS negative_one_percentage, " +
            " ROUND(SUM(1) * 100.0 / SUM(1), 2) AS total_percentage, " +
            " SUM(1) AS total " + // Ajoutez cette ligne pour obtenir le total en chiffres
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE s.id = :siteId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id")
    List<Object[]> findByYearSite(@Param("year") int year, @Param("siteId") Long siteId);

    //deuxieme indicateur patient testés
    //pour chaque region
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " COUNT(DISTINCT p.id) AS total_patients, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(COUNT(DISTINCT p.id) * 100.0 / COUNT(DISTINCT p.id), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE r.id = :regionId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> findByYearByPatient(@Param("year") int year, @Param("regionId") Long regionId);

    // pour chaque district
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " COUNT(DISTINCT p.id) AS total_patients, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(COUNT(DISTINCT p.id) * 100.0 / COUNT(DISTINCT p.id), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE d.id = :districtId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> findByYearByPatientByDistrict(@Param("year") int year, @Param("districtId") Long districtId);

    // pour chaque site
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " COUNT(DISTINCT p.id) AS total_patients, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(COUNT(DISTINCT p.id) * 100.0 / COUNT(DISTINCT p.id), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE s.id = :siteId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> findByYearByPatientBySite(@Param("year") int year, @Param("siteId") Long siteId);


//pour toute les regions
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" COUNT(DISTINCT p.id) AS total_patients, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS LL_percentage, " +
" ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS less_than_1000_percentage, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(DISTINCT p.id), 2) AS greater_than_or_equal_1000_percentage, " +
" ROUND(COUNT(DISTINCT p.id) * 100.0 / COUNT(DISTINCT p.id), 2) AS total_percentage " +
"FROM District d " +
"JOIN Site s ON d.id = s.district.id " +
"JOIN Patient p ON s.id = p.site.id " +
"JOIN Analysis a ON p.id = a.patient.id " +
"JOIN Test t ON a.test.id = t.id " +
"JOIN Regimen reg ON a.regimen.id = reg.id " +
"JOIN VlReason vl ON a.vlReason.id = vl.id " +
"WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
"GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> findByYearByPatientByTouteRegion(@Param("year") int year);

//INDICATEUR MOTIF DE LA DEMANDE DE TESTS REALISES pour toutes les regions
@Query(
        "SELECT " +
                "'CV contrôle sous ARV', " +
                "ROUND(SUM(CASE WHEN vl.name = 'CV contrôle sous ARV' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS cvControleSousARV, " +
                "'Autres', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS Autres, " +
                "'Echec clinique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec clinique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecClinique, " +
                "'Echec immunologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec immunologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecImmunologique, " +
                "'Echec virologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec virologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecVirologique, " +
                "'Autres (à préciser)', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres (à préciser)' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS autres, " +
                "'', " +
                "ROUND(SUM(CASE WHEN vl.name = '' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS invalide " +
                "FROM Region r " +
                "JOIN District d ON r.id = d.region.id " +
                "JOIN Site s ON d.id = s.district.id " +
                "JOIN Patient p ON s.id = p.site.id " +
                "JOIN Analysis a ON p.id = a.patient.id " +
                "JOIN Test t ON a.test.id = t.id " +
                "JOIN Regimen reg ON a.regimen.id = reg.id " +
                "JOIN VlReason vl ON a.vlReason.id = vl.id " +
                "WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
                "GROUP BY 1"
)
List<Object[]> motifVlreasonByAllRegions(@Param("year") int year);


// INDICATEUR MOTIF DE LA DEMANDE DE TESTS REALISES pour chaque region
@Query(
        "SELECT " +
                "'CV contrôle sous ARV', " +
                "ROUND(SUM(CASE WHEN vl.name = 'CV contrôle sous ARV' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS cvControleSousARV, " +
                "'Autres', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS Autres, " +
                "'Echec clinique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec clinique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecClinique, " +
                "'Echec immunologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec immunologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecImmunologique, " +
                "'Echec virologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec virologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecVirologique, " +
                "'Autres (à préciser)', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres (à préciser)' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS autres, " +
                "'', " +
                "ROUND(SUM(CASE WHEN vl.name = '' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS invalide " +
                "FROM Region r " +
                "JOIN District d ON r.id = d.region.id " +
                "JOIN Site s ON d.id = s.district.id " +
                "JOIN Patient p ON s.id = p.site.id " +
                "JOIN Analysis a ON p.id = a.patient.id " +
                "JOIN Test t ON a.test.id = t.id " +
                "JOIN Regimen reg ON a.regimen.id = reg.id " +
                "JOIN VlReason vl ON a.vlReason.id = vl.id " +
                "WHERE r.id = :regionId AND " +
                "EXTRACT(YEAR FROM a.drcpt) = :year"
)
List<Object[]> motifVlreasonByOneRegion(@Param("year") int year, @Param("regionId") Long regionId);


// INDICATEUR MOTIF DE LA DEMANDE DE TESTS REALISES pour chaque district
@Query(
        "SELECT " +
                "'CV contrôle sous ARV', " +
                "ROUND(SUM(CASE WHEN vl.name = 'CV contrôle sous ARV' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS cvControleSousARV, " +
                "'Autres', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS Autres, " +
                "'Echec clinique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec clinique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecClinique, " +
                "'Echec immunologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec immunologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecImmunologique, " +
                "'Echec virologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec virologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecVirologique, " +
                "'Autres (à préciser)', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres (à préciser)' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS autres, " +
                "'', " +
                "ROUND(SUM(CASE WHEN vl.name = '' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS invalide " +
                "FROM Region r " +
                "JOIN District d ON r.id = d.region.id " +
                "JOIN Site s ON d.id = s.district.id " +
                "JOIN Patient p ON s.id = p.site.id " +
                "JOIN Analysis a ON p.id = a.patient.id " +
                "JOIN Test t ON a.test.id = t.id " +
                "JOIN Regimen reg ON a.regimen.id = reg.id " +
                "JOIN VlReason vl ON a.vlReason.id = vl.id " +
                "WHERE d.id = :districtId AND " +
                "EXTRACT(YEAR FROM a.drcpt) = :year"
)
List<Object[]> motifVlreasonByDistrict(@Param("year") int year, @Param("districtId") Long districtId);

// INDICATEUR MOTIF DE LA DEMANDE DE TESTS REALISES pour chaque site
@Query(
        "SELECT " +
                "'CV contrôle sous ARV', " +
                "ROUND(SUM(CASE WHEN vl.name = 'CV contrôle sous ARV' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS cvControleSousARV, " +
                "'Autres', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS Autres, " +
                "'Echec clinique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec clinique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecClinique, " +
                "'Echec immunologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec immunologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecImmunologique, " +
                "'Echec virologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec virologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecVirologique, " +
                "'Autres (à préciser)', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres (à préciser)' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS autres, " +
                "'', " +
                "ROUND(SUM(CASE WHEN vl.name = '' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS invalide " +
                "FROM Region r " +
                "JOIN District d ON r.id = d.region.id " +
                "JOIN Site s ON d.id = s.district.id " +
                "JOIN Patient p ON s.id = p.site.id " +
                "JOIN Analysis a ON p.id = a.patient.id " +
                "JOIN Test t ON a.test.id = t.id " +
                "JOIN Regimen reg ON a.regimen.id = reg.id " +
                "JOIN VlReason vl ON a.vlReason.id = vl.id " +
                "WHERE s.id = :siteId AND " +
                "EXTRACT(YEAR FROM a.drcpt) = :year"
)
List<Object[]> motifVlreasonBySite(@Param("year") int year, @Param("siteId") Long siteId);

    //Nombres totals de tests realisés
    @Query("SELECT COUNT(t.id) " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE EXTRACT(YEAR FROM a.drcpt) = :year")
    Long getTotalTestsByYear(@Param("year") int year);

    //INDICATEUR NOMBRE DE TESTS REALISES PAR REGION il affichera la liste des regions et leur differents resultats
    @Query(value = "SELECT " +
            " r.name AS region_name, " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
            " COUNT(*) AS total_tests, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS minus_1_percentage, " +
            " ROUND(COUNT(*) * 100.0 / COUNT(*), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id, r.name, EXTRACT(YEAR FROM a.drcpt) " +
            "ORDER BY total_tests DESC")
     List<Object[]>listeTestByRegion(@Param("year") int year);

     
    //INDICATEUR NOMBRE DE TESTS REALISES PAR DIstrict il affichera la liste des districts et leur differents resultats
    @Query(value = "SELECT " +
    " d.name AS district_name, " +
    " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
    " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
    " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
    " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
    " COUNT(*) AS total_tests, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS LL_percentage, " +
    " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS less_than_1000_percentage, " +
    " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS greater_than_or_equal_1000_percentage, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS minus_1_percentage, " +
    " ROUND(COUNT(*) * 100.0 / COUNT(*), 2) AS total_percentage " +
    "FROM Region r " +
    "JOIN District d ON r.id = d.region.id " +
    "JOIN Site s ON d.id = s.district.id " +
    "JOIN Patient p ON s.id = p.site.id " +
    "JOIN Analysis a ON p.id = a.patient.id " +
    "JOIN Test t ON a.test.id = t.id " +
    "JOIN Regimen reg ON a.regimen.id = reg.id " +
    "JOIN VlReason vl ON a.vlReason.id = vl.id " +
    "WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
    "GROUP BY d.id, d.name, EXTRACT(YEAR FROM a.drcpt) " +
    "ORDER BY total_tests DESC")
List<Object[]>listeTestByDistrict(@Param("year") int year);


    //INDICATEUR NOMBRE DE TESTS REALISES PAR SIte il affichera la liste des sites et leur differents resultats
    @Query(value = "SELECT " +
            " s.nameSite AS site_name, " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
            " COUNT(*) AS total_tests, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS minus_1_percentage, " +
            " ROUND(COUNT(*) * 100.0 / COUNT(*), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY s.id, s.nameSite, EXTRACT(YEAR FROM a.drcpt) " +
            "ORDER BY total_tests DESC")
    List<Object[]>listeTestBySite(@Param("year") int year);

    
    //INDICATEUR NOMBRE DE TESTS REALISES PAR Site en fonction du district
    @Query(value = "SELECT " +
            " s.nameSite AS site_name, " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
            " COUNT(*) AS total_tests, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS minus_1_percentage, " +
            " ROUND(COUNT(*) * 100.0 / COUNT(*), 2) AS total_percentage " +
            "FROM District d " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
            "AND d.id = :districtId " +
            "GROUP BY s.id, s.nameSite " +
            "ORDER BY total_tests DESC")
    List<Object[]> listeTestBySiteInDistrict(@Param("year") int year, @Param("districtId") Long districtId);


    //nombre de  tests par genre
    //genre masculin

    @Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND p.gender = :sex AND  " +
            " EXTRACT(YEAR FROM a.drcpt) = :year " +
            " GROUP BY r.id, r.name, p.gender " +
            " order by r.name;", nativeQuery = true)
    List<Object[]> getAnalysisMaleForRegionWithSpecificYearM(@Param("regionId") Long regionId , @Param("year") int year, @Param("sex") String sex);

    //NOmbre de Tets realisés par genre Feminin par region
    @Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY r.id, r.name, p.gender " +
            " order by r.name;", nativeQuery = true)
    List<Object[]> getAnalysisFemaleForRegionWithSpecificYear(@Param("regionId") Long regionId , @Param("year") int year, @Param("sex") String sex);

    
    @Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE d.id = :districtId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY d.id, d.name, p.gender " +
            " order by d.name;", nativeQuery = true)
    List<Object[]> getAnalysisMaleForDistrictWithYear(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );

    @Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
    " FROM dashboard.region r " +
    " INNER JOIN dashboard.district d ON r.id = d.region_id" +
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id " +
    " WHERE d.id = :districtId AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY d.id, d.name, p.gender " +
    " order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForDistrictWithYear(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );

    //PAR SITE
    @Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE s.id = :siteId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY s.id, s.new_site_long_name, p.gender " +
            " order by s.new_site_long_name;", nativeQuery = true)
    List<Object[]> getAnalysisMaleForSiteWithYear(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex );

    @Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE s.id = :siteId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY s.id, s.new_site_long_name, p.gender " +
            " order by s.new_site_long_name;", nativeQuery = true)
    List<Object[]> getAnalysisFemaleForSiteWithYear(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex);

 //Nombre de patients testés par tranche d'âge selon une region spécifique  - Category national
//Age inférieur à 2
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE r.id = :regionId AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " + 
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 2 et 9
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 10 et 14
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 15 et 19
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 20 et 24
@Query(value = "SELECT " +
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
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age >25
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Nombre de patients testés par tranche d'âge pour toute region - Category national
 //Age inférieur à 2
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeMinusTwoForAllRegion(@Param("year") int year);
 
 //Age compris entre 2 et 9
 @Query(value = "SELECT " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForAllRegion(@Param("year") int year);

//Age compris entre 2 et 9
@Query(value = "SELECT " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE d.id = :districtId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneDistrict(@Param("districtId") Long districtId, @Param("year") int year);


//Age compris entre 2 et 9
@Query(value = "SELECT " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwoAndNineForOneSite(@Param("siteId") Long siteId, @Param("year") int year);


 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForAllRegion(@Param("year") int year);

 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneDistrict(@Param("districtId") Long districtId, @Param("year") int year);



 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE s.id = :siteId AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTenAndFourteenForOneSite(@Param("siteId") Long siteId, @Param("year") int year);



 //Age compris entre 15 et 19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);

//Age compris entre 15 et 19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE d.id = :districtId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneDistrict(@Param("districtId") Long districtId, @Param("year") int year);


//Age compris entre 15 et 19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenFifteenAndNineteenForOneSite(@Param("siteId") Long siteId, @Param("year") int year);


 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);


 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourFoOneDistrict(@Param("districtId") Long districtId, @Param("year") int year);


 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE s.id = :siteId AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeBetweenTwentyAndTwentyFourForOneSite(@Param("siteId") Long siteId, @Param("year") int year);

 //Age >25
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForAllRegion(@Param("year") int year);

//Age >25
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE d.id = :districtId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneDistric(@Param("districtId") Long districtId, @Param("year") int year);


//Age >25
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeGreaterThanTwentyFiveForOneSite(@Param("siteId") Long siteId, @Param("year") int year);



  //Nombre de patients testés par tranche d'âge selon une region spécifique  - CDC CI

  //Age inférieur à 1
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 1 et 4
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
 
 
 //Age compris entre 5 et 9
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 15 et 19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);



 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 25 et 29
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 30 et 34
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 34 et 39
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 34 AND 39 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 40 et 44
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 45 et 49
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age supérieur à 50
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE r.id = :regionId AND " +
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


   //Nombre de patients testés par tranche d'âge pour toute region - CDC CI
//Age inférieur à 1
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryMinusTwoForAllRegion(@Param("year") int year);

//Age compris entre 1 et 4
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4  AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenOneAndFourForAllegion(@Param("year") int year);


//Age compris entre 5 et 9
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenFiveAndNineForAllRegion(@Param("year") int year);


//Age compris entre 10 et 14
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenTenAndFourteenForAllRegion(@Param("year") int year);

//Age compris entre 15 et 19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedyAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);

//Age compris entre 20 et 24
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);


//Age compris entre 25 et 29
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@Param("year") int year);

//Age compris entre 30 et 34
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@Param("year") int year);


//Age compris entre 34 et 39
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 34 AND 39 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@Param("year") int year);


//Age compris entre 40 et 44
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@Param("year") int year);


//Age compris entre 45 et 49
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@Param("year") int year);

//Age supérieur à 50
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getPatientTestedByAgeCategoryGreaterThanFiftyForAllRegion(@Param("year") int year);

    /*----------------------------------------------------------------------------------------------------------------------*/

 //Nombre de tests réalisés par tranche d'âge selon une region spécifique  - Category national
 //Age inférieur à 2
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE r.id = :regionId AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " + 
" GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 2 et 9
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
        " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTwoAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 10 et 14
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
        " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 15 et 19
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
        " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 20 et 24
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
        " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age >25
@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
        " SUM(1) AS TOTAL_ANALYSE, " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
        " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
        " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
        " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeGreaterThanTwentyFiveForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);



 //Nombre de tests réalisés par tranche d'âge pour toute region  - Category national
 //Age inférieur à 2
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeMinusTwoForAllRegion(@Param("year") int year);
 
 //Age compris entre 2 et 9
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTwoAndNineForAllRegion(@Param("year") int year);


 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTenAndFourteenForAllRegion(@Param("year") int year);


 //Age compris entre 15 et 19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);

 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);

 //Age >25
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
 " SUM(1) AS TOTAL_ANALYSE, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestByAgeGreaterThanTwentyFiveForAllRegion(@Param("year") int year);


 //Nombre de tests réalisés par tranche d'âge selon une region spécifique  - CDC CI
 //Age inférieur à 1
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryMinusTwoForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 1 et 4
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenOneAndFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);
 
 
 //Age compris entre 5 et 9
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 15 et 19
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);



 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 25 et 29
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age compris entre 30 et 34
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 34 et 39
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 34 AND 39 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 40 et 44
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Age compris entre 45 et 49
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);

 //Age supérieur à 50
 @Query(value = "SELECT " +
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
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + 
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryGreaterThanFiftyForOneRegion(@Param("regionId") Long regionId, @Param("year") int year);


 //Nombre de tests réalisés par tranche d'âge pour toute region - CDC CI
 //Age inférieur à 1
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryMinusTwoForAllRegion(@Param("year") int year);

 //Age compris entre 1 et 4
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4  AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenOneAndFourForAllegion(@Param("year") int year);
 
 
 //Age compris entre 5 et 9
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFiveAndNineForAllRegion(@Param("year") int year);


 //Age compris entre 10 et 14
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTenAndFourteenForAllRegion(@Param("year") int year);

 //Age compris entre 15 et 19
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);

 //Age compris entre 20 et 24
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);


 //Age compris entre 25 et 29
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@Param("year") int year);

 //Age compris entre 30 et 34
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@Param("year") int year);


 //Age compris entre 34 et 39
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 34 AND 39 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenThirtyFourAndThirtyNineAllRegion(@Param("year") int year);


 //Age compris entre 40 et 44
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@Param("year") int year);


 //Age compris entre 45 et 49
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@Param("year") int year);

 //Age supérieur à 50
 @Query(value = "SELECT " +
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
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year  " +
 " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestByAgeCategoryGreaterThanFiftyForAllRegion(@Param("year") int year);

    /*----------------------------------------------------------------------------------------------------------------------*/

@Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE r.id = :regionId AND p.gender = :sex AND  " +
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY r.id, r.name, p.gender " +
" order by r.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForRegionWithSpecificYear(@Param("regionId") Long regionId , @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id " +
    " WHERE d.id = :districtId AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY d.id, d.name, p.gender " +
    " order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForDistrictWithSpecificYear(@Param("districtId") Long districtId ,  @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id " +
    " WHERE d.id = :districtId AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY d.id, d.name, p.gender " +
    " order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForDistrictWithSpecificYear(@Param("districtId") Long districtId ,  @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT s.id, s.new_site_long_name AS new_site_long_name, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id " +
    " WHERE s.id = :siteId AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY s.id, s.new_site_long_name, p.gender " +
    " order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForSiteWithSpecificYear(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT s.id, s.new_site_long_name AS new_site_long_name, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id " +
    " WHERE s.id = :siteId AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY s.id, s.new_site_long_name, p.gender " +
    " order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForSiteWithSpecificYear(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id " + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id " + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND" +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY p.gender " +
    " order by p.gender;", nativeQuery = true)
List<Object[]> getAnalysisMaleByRegionWithSpecificYear(@Param("year") int year, @Param("sex") String sex); 



@Query(value = "SELECT p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id " + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id " + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY p.gender " +
    " order by p.gender;", nativeQuery = true)
List<Object[]> getAnalysisFemaleByRegionWithSpecificYear(@Param("year") int year, @Param("sex") String sex); 


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY d.id, d.name, p.gender " +
    " order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleDistrictWithSpecificYear(@Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY d.id, d.name, p.gender " +
    " order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleByDistrictWithSpecificYear(@Param("year") int year, @Param("sex") String sex);

@Query(value = "SELECT s.id, s.new_site_long_name AS new_site_long_name, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY s.id, s.new_site_long_name, p.gender " +
    " order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisMaleBySiteWithSpecificYear(@Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT s.id, s.new_site_long_name AS new_site_long_name, p.gender AS genre, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
    " SUM(1) AS TOTAL_ANALYSE, " +
    " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
    " FROM dashboard.region r " + 
    " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
    " INNER JOIN dashboard.site s ON d.id = s.district_id " +
    " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
    " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
    " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
    " EXTRACT(YEAR FROM a.drcpt) = :year  " +
    " GROUP BY s.id, s.new_site_long_name, p.gender " +
    " order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleBySiteWithSpecificYear(@Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE r.id = :regionId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, r.name, p.gender " +
" order by r.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForRegionWithYear(@Param("regionId") Long regionId, @Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE r.id = :regionId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY r.id, r.name, p.gender " +
" order by r.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForRegionWithYear(@Param("regionId") Long regionId, @Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE d.id = :districtId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY d.id, d.name, p.gender " +
" order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForDistrictWithYearC(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE d.id = :districtId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY d.id, d.name, p.gender " +
" order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForDistrictWithYearC(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );



@Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE s.id = :siteId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY s.id, s.new_site_long_name, p.gender " +
" order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisMaleForSiteWithYearC(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex );


@Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE s.id = :siteId AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY s.id, s.new_site_long_name, p.gender " +
" order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleForSiteWithYearC(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT p.gender as genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY p.gender " +
" order by p.gender;", nativeQuery = true)
List<Object[]> getAnalysisMaleByRegionWithYear(@Param("year") int year,  @Param("sex") String sex);


@Query(value = "SELECT p.gender as genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY p.gender " +
" order by p.gender;", nativeQuery = true)
List<Object[]> getAnalysisFemaleByRegionWithYear(@Param("year") int year, @Param("sex") String sex);



@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND  " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY d.id, d.name, p.gender " +
" order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisMaleByDistrictWithYear(@Param("year") int year, @Param("sex") String sex);



@Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND  " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY d.id, d.name, p.gender " +
" order by d.name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleByDistrictWithYear(@Param("year") int year, @Param("sex") String sex);



@Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY s.id, s.new_site_long_name, p.gender " +
" order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisMaleBySiteWithYear(@Param("year") int year, @Param("sex") String sex);


@Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS NOMBRE_INVALIDE, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " + 
" INNER JOIN dashboard.district d ON r.id = d.region_id" + 
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY s.id, s.new_site_long_name, p.gender " +
" order by s.new_site_long_name;", nativeQuery = true)
List<Object[]> getAnalysisFemaleSiteWithYear(@Param("year") int year, @Param("sex") String sex);

@Query(value = "SELECT p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id " +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id " +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND" +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY p.gender " +
            " order by p.gender;", nativeQuery = true)
    List<Object[]> getAnalysisPatientMaleByRegionWithSpecificYear(@Param("year") int year, @Param("sex") String sex);

    @Query(value = "SELECT p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id " +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id " +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY p.gender " +
            " order by p.gender;", nativeQuery = true)
    List<Object[]> getAnalysisPatientFemaleByRegionWithSpecificYear(@Param("year") int year, @Param("sex") String sex);

    
    //  ceci est pour test c'est un  exemple ne compte pas dans le code
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count, " +
            " COUNT(*) AS total_tests, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS LL_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS less_than_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS greater_than_or_equal_1000_percentage, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS minus_1_percentage, " +
            " ROUND(COUNT(*) * 100.0 / COUNT(*), 2) AS total_percentage " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE r.id = :regionId AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year " +
            "GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> findByYearByRegiontest(@Param("year") int year, @Param("regionId") Long regionId);

/* 
    @Query(value = "SELECT  " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " + 
            " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birth_date)) < 2 " + 
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
     List<Object[]> getTestedPatientByAgeCategoryNationalForAllRegions(@Param("year") int year, @Param("regionId") Long regionId);
 */
     @Query(value = "SELECT r.id as region_id, r.name as region_name, p.birth_date as date_anniversaire " +
     " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS NOMBRE_INDETECTABLE_LL, " +
     " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS NOMBRE_SUPPRIME, " +
     " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
     " SUM(1) AS TOTAL_ANALYSE, " +
     " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
     " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME " +
     " FROM dashboard.region r " + 
     " INNER JOIN dashboard.district d ON r.id = d.region_id" + 
     " INNER JOIN dashboard.site s ON d.id = s.district_id " +
     " INNER JOIN dashboard.patient p ON s.id = p.site_id" + 
     " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
     " INNER join dashboard.test t on t.id = a.test_id AND " +
     " WHERE r.id = :regionId TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) <2 AND " +
     " TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN 2 AND 9 AND " +
     " TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN 10 AND 14 AND " +
     " TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN 15 AND 19 AND " +
     " TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN 20 AND 24 AND " +
     " TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) >25 AND" +
     " EXTRACT(YEAR FROM a.drcpt) = :year " +
     " GROUP BY p.birth_date " +
     " ORDER BY p.birth_date;", nativeQuery = true)
List<Object[]> getTestedPatientByAgeCategoryNationalForOneRegion(@Param("year") int year);


     

/* @Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> test(@Param("regionId") Long regionId, @Param("year") int year);
 */

@Query(value = "SELECT " +
        " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
        " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
        " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
        " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
        " FROM Region r " +
        " JOIN District d ON r.id = d.region.id " +
        " JOIN Site s ON d.id = s.district.id " +
        " JOIN Patient p ON s.id = p.site.id " +
        " JOIN Analysis a ON p.id = a.patient.id " +
        " WHERE r.id = :regionId AND " +
        " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
        " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + 
        " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> test(@Param("regionId") Long regionId, @Param("year") int year);

/************************************************************************************************************************************ */

    //INDICATEUR : tendance de tests realisés en fonction de la charge virale

    @Query("SELECT YEAR(a.drcpt) AS year, " +
            "CASE " +
            "WHEN MONTH(a.drcpt) = 1 THEN 'JAN' " +
            "WHEN MONTH(a.drcpt) = 2 THEN 'FEB' " +
            "WHEN MONTH(a.drcpt) = 3 THEN 'MAR' " +
            "WHEN MONTH(a.drcpt) = 4 THEN 'APR' " +
            "WHEN MONTH(a.drcpt) = 5 THEN 'MAY' " +
            "WHEN MONTH(a.drcpt) = 6 THEN 'JUN' " +
            "WHEN MONTH(a.drcpt) = 7 THEN 'JUL' " +
            "WHEN MONTH(a.drcpt) = 8 THEN 'AUG' " +
            "WHEN MONTH(a.drcpt) = 9 THEN 'SEP' " +
            "WHEN MONTH(a.drcpt) = 10 THEN 'OCT' " +
            "WHEN MONTH(a.drcpt) = 11 THEN 'NOV' " +
            "ELSE 'DEC' END AS month, " +
            "SUM(a.convertedResult) AS total_converted_result, " +
            "COUNT(*) AS total_tests " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE YEAR(a.drcpt) = 2021 " +
            "GROUP BY YEAR(a.drcpt), MONTH(a.drcpt) " +
            "ORDER BY YEAR(a.drcpt), MONTH(a.drcpt)")
    List<Object[]> tendancetest2021();




    @Query("SELECT YEAR(a.drcpt) AS year, " +
            "CASE " +
            "WHEN MONTH(a.drcpt) = 1 THEN 'JAN' " +
            "WHEN MONTH(a.drcpt) = 2 THEN 'FEB' " +
            "WHEN MONTH(a.drcpt) = 3 THEN 'MAR' " +
            "WHEN MONTH(a.drcpt) = 4 THEN 'APR' " +
            "WHEN MONTH(a.drcpt) = 5 THEN 'MAY' " +
            "WHEN MONTH(a.drcpt) = 6 THEN 'JUN' " +
            "WHEN MONTH(a.drcpt) = 7 THEN 'JUL' " +
            "WHEN MONTH(a.drcpt) = 8 THEN 'AUG' " +
            "WHEN MONTH(a.drcpt) = 9 THEN 'SEP' " +
            "WHEN MONTH(a.drcpt) = 10 THEN 'OCT' " +
            "WHEN MONTH(a.drcpt) = 11 THEN 'NOV' " +
            "ELSE 'DEC' END AS month, " +
            "SUM(a.convertedResult) AS total_converted_result, " +
            "COUNT(*) AS total_tests " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE YEAR(a.drcpt) = 2022 " +
            "GROUP BY YEAR(a.drcpt), MONTH(a.drcpt) " +
            "ORDER BY YEAR(a.drcpt), MONTH(a.drcpt)")
    List<Object[]> tendancetest2022();



    @Query("SELECT YEAR(a.drcpt) AS year, " +
            "CASE " +
            "WHEN MONTH(a.drcpt) = 1 THEN 'JAN' " +
            "WHEN MONTH(a.drcpt) = 2 THEN 'FEB' " +
            "WHEN MONTH(a.drcpt) = 3 THEN 'MAR' " +
            "WHEN MONTH(a.drcpt) = 4 THEN 'APR' " +
            "WHEN MONTH(a.drcpt) = 5 THEN 'MAY' " +
            "WHEN MONTH(a.drcpt) = 6 THEN 'JUN' " +
            "WHEN MONTH(a.drcpt) = 7 THEN 'JUL' " +
            "WHEN MONTH(a.drcpt) = 8 THEN 'AUG' " +
            "WHEN MONTH(a.drcpt) = 9 THEN 'SEP' " +
            "WHEN MONTH(a.drcpt) = 10 THEN 'OCT' " +
            "WHEN MONTH(a.drcpt) = 11 THEN 'NOV' " +
            "ELSE 'DEC' END AS month, " +
            "SUM(a.convertedResult) AS total_converted_result, " +
            "COUNT(*) AS total_tests " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "JOIN Test t ON a.test.id = t.id " +
            "JOIN Regimen reg ON a.regimen.id = reg.id " +
            "JOIN VlReason vl ON a.vlReason.id = vl.id " +
            "WHERE YEAR(a.drcpt) = 2023 " +
            "GROUP BY YEAR(a.drcpt), MONTH(a.drcpt) " +
            "ORDER BY YEAR(a.drcpt), MONTH(a.drcpt)")
    List<Object[]> tendancetest2023();



    //tests realisés par tranche dage <2
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " +
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodName(@Param("regionId") Long regionId, @Param("year") int year);


    //tests realisés par tranche dage 1-4
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " +
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameOne(@Param("regionId") Long regionId, @Param("year") int year);



    //tests realisés par tranche dage 5-9
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNametwo(@Param("regionId") Long regionId, @Param("year") int year);




    //tests realisés par tranche dage 10-14
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNamethree(@Param("regionId") Long regionId, @Param("year") int year);


    //tests realisés par tranche dage 15-19
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNamefour(@Param("regionId") Long regionId, @Param("year") int year);




    //tests realisés par tranche dage 20-24
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameFive(@Param("regionId") Long regionId, @Param("year") int year);




    //tests realisés par tranche dage 25-29
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameSix(@Param("regionId") Long regionId, @Param("year") int year);



    //tests realisés par tranche dage 30-34
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameseven(@Param("regionId") Long regionId, @Param("year") int year);



    //tests realisés par tranche dage 25-39
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39" + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameheight(@Param("regionId") Long regionId, @Param("year") int year);



    //tests realisés par tranche dage 40-44
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNamenine(@Param("regionId") Long regionId, @Param("year") int year);



    //tests realisés par tranche dage 45-49
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameten(@Param("regionId") Long regionId, @Param("year") int year);





    //tests realisés par tranche dage >50

    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE r.id = :regionId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + // Condition pour la tranche d'âge de plus de 50 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodNameeleven(@Param("regionId") Long regionId, @Param("year") int year);




    //Tests realisés par tranche par district



    //tests realisés par tranche dage <2
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + // Nouvelle condition pour la tranche d'âge < 2 ans
            " GROUP BY r.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictName(@Param("districtId") Long districtId, @Param("year") int year);


    //tests realisés par tranche dage 1-4
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameOne(@Param("districtId") Long districtId, @Param("year") int year);



    //tests realisés par tranche dage 5-9
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNametwo(@Param("districtId") Long districtId, @Param("year") int year);




    //tests realisés par tranche dage 10-14
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNamethree(@Param("districtId") Long districtId, @Param("year") int year);


    //tests realisés par tranche dage 15-19
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNamefour(@Param("districtId") Long districtId, @Param("year") int year);




    //tests realisés par tranche dage 20-24
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameFive(@Param("districtId") Long districtId, @Param("year") int year);




    //tests realisés par tranche dage 25-29
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameSix(@Param("districtId") Long districtId, @Param("year") int year);



    //tests realisés par tranche dage 30-34
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameseven(@Param("districtId") Long districtId, @Param("year") int year);



    //tests realisés par tranche dage 25-39
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39" + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameheight(@Param("districtId") Long districtId, @Param("year") int year);



    //tests realisés par tranche dage 40-44
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNamenine(@Param("districtId") Long districtId, @Param("year") int year);



    //tests realisés par tranche dage 45-49
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameten(@Param("districtId") Long districtId, @Param("year") int year);


    //tests realisés par tranche dage >50

    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + // Condition pour la tranche d'âge de plus de 50 ans
            " GROUP BY d.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodDistrictNameeleven(@Param("districtId") Long districtId, @Param("year") int year);















    //Tests realisés par tranche par site



    //tests realisés par tranche dage <2
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + // Nouvelle condition pour la tranche d'âge < 2 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteName(@Param("siteId") Long siteId, @Param("year") int year);


    //tests realisés par tranche dage 1-4
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameOne(@Param("siteId") Long siteId, @Param("year") int year);



    //tests realisés par tranche dage 5-9
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNametwo(@Param("siteId") Long siteId, @Param("year") int year);




    //tests realisés par tranche dage 10-14
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNamethree(@Param("siteId") Long siteId, @Param("year") int year);


    //tests realisés par tranche dage 15-19
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNamefour(@Param("siteId") Long siteId, @Param("year") int year);




    //tests realisés par tranche dage 20-24
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameFive(@Param("siteId") Long siteId, @Param("year") int year);




    //tests realisés par tranche dage 25-29
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameSix(@Param("siteId") Long siteId, @Param("year") int year);



    //tests realisés par tranche dage 30-34
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameseven(@Param("siteId") Long siteId, @Param("year") int year);



    //tests realisés par tranche dage 25-39
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39" + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameheight(@Param("siteId") Long siteId, @Param("year") int year);



    //tests realisés par tranche dage 40-44
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNamenine(@Param("siteId") Long siteId, @Param("year") int year);



    //tests realisés par tranche dage 45-49
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + // Condition pour la tranche d'âge de 4 à 9 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameten(@Param("siteId") Long siteId, @Param("year") int year);


    //tests realisés par tranche dage >50

    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            " FROM Region r " +
            " JOIN District d ON r.id = d.region.id " +
            " JOIN Site s ON d.id = s.district.id " +
            " JOIN Patient p ON s.id = p.site.id " +
            " JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE s.id = :siteId AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + // Condition pour la tranche d'âge de plus de 50 ans
            " GROUP BY s.id, EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]> yourMethodSiteNameeleven(@Param("siteId") Long siteId, @Param("year") int year);





    // tests realisés par tranche d age cette fois pour toute les regions


       @Query(value = "SELECT " +
               "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            "FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 "  // Nouvelle condition pour la tranche d'âge < 2 ans
           )
          List<Object[]> MethodRegion(@Param("year") int year);




    //tests realisés par tranche dage 1-4

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " // Nouvelle condition pour la tranche d'âge < 2 ans
           )
    List<Object[]> MethodRegionOne(@Param("year") int year);



    //tests realisés par tranche dage 5-9

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " // Nouvelle condition pour la tranche d'âge < 2 ans
           )
      List<Object[]> MethodRegionThree(@Param("year") int year);



    //tests realisés par tranche dage 10-14

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " // Nouvelle condition pour la tranche d'âge < 2 ans
         )
    List<Object[]> MethodRegionfour(@Param("year") int year);




    //tests realisés par tranche dage 10-14

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " // Nouvelle condition pour la tranche d'âge < 2 ans
        )
    List<Object[]> MethodRegionfive(@Param("year") int year);





    //tests realisés par tranche dage 20-24

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " // Nouvelle condition pour la tranche d'âge < 2 ans
       )
    List<Object[]> MethodRegionsix(@Param("year") int year);




    //tests realisés par tranche dage 25-29

    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " // Nouvelle condition pour la tranche d'âge < 2 ans
            )
    List<Object[]> MethodRegionseven(@Param("year") int year);





    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " // Nouvelle condition pour la tranche d'âge < 2 ans
            )
    List<Object[]> MethodRegionheight(@Param("year") int year);





    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39 " // Nouvelle condition pour la tranche d'âge < 2 ans
          )
    List<Object[]> MethodRegionnine(@Param("year") int year);


    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " // Nouvelle condition pour la tranche d'âge < 2 ans
            )
    List<Object[]> MethodRegionten(@Param("year") int year);




    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 ")
    List<Object[]> MethodRegioneleven(@Param("year") int year);




    @Query(value = "SELECT " +
            "'touteregion' AS regionName, " +
            "SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS LL_count, " +
            "SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) AS less_than_1000_count, " +
            "SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS greater_than_or_equal_1000_count, " +
            "SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS minus_1_count " +
            "FROM Region r " +
            "JOIN District d ON r.id = d.region.id " +
            "JOIN Site s ON d.id = s.district.id " +
            "JOIN Patient p ON s.id = p.site.id " +
            "JOIN Analysis a ON p.id = a.patient.id " +
            "WHERE r.id IN (SELECT r.id FROM Region r) AND " +
            "EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            "FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 ") // Nouvelle condition pour la tranche d'âge < 2 ans
    List<Object[]> MethodRegiontwelve(@Param("year") int year);

/********************************************************************************************************************************** */
    //par district

    @Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE d.id = :districtId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY d.id, d.name, p.gender " +
            " order by d.name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientMaleForDistrictWithYear(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );


    @Query(value = "SELECT d.id, d.name AS nom_district, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE d.id = :districtId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY d.id, d.name, p.gender " +
            " order by d.name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientFemaleForDistrictWithYear(@Param("districtId") Long districtId , @Param("year") int year, @Param("sex") String sex );

    

    // pour chaque region

    @Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +

            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY r.id, r.name, p.gender " +
            " order by r.name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientMaleForRegionWithYearM(@Param("regionId") Long regionId, @Param("year") int year, @Param("sex") String sex );


    @Query(value = "SELECT r.id, r.name AS nom_region, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY r.id, r.name, p.gender " +
            " order by r.name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientFemaleForRegionWithYearF(@Param("regionId") Long regionId, @Param("year") int year, @Param("sex") String sex );


    // par site


    @Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE s.id = :siteId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY s.id, s.new_site_long_name, p.gender " +
            " order by s.new_site_long_name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientMaleForSiteWithYearSiteM(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex );

    @Query(value = "SELECT s.id, s.new_site_long_name AS nom_site, p.gender AS genre, " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS NOMBRE_NON_SUPPRIME, " +
            " SUM(1) AS TOTAL_ANALYSE, " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE s.id = :siteId AND p.gender = :sex AND " +
            " EXTRACT(YEAR FROM a.drcpt) = :year  " +
            " GROUP BY s.id, s.new_site_long_name, p.gender " +
            " order by s.new_site_long_name;", nativeQuery = true)
    List<Object[]> getAnalysisPatientFemaleForSiteWithYearsiteF(@Param("siteId") Long siteId , @Param("year") int year, @Param("sex") String sex);


 //Nombre de patients testés par region
 @Query(value = "SELECT r.id, r.name," +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY r.id, r.name, EXTRACT(YEAR FROM a.drcpt)" +
 " ORDER BY TOTAL_INDETECTABLE_LL DESC, TOTAL_SUPPRIME DESC, TOTAL_NON_SUPPRIME DESC")
 List<Object[]> getPatientForAllRegion(@Param("year") int year);


 //Nombre de tests réalisés par district
 @Query(value = "SELECT d.id, d.name," +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY d.id, d.name, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getPatientForDistrict(@Param("districtId") Long districtId , @Param("year") int year);


 //Nombre de tests réalisés par site pour un district
 @Query(value = "SELECT s.id, SUBSTRING(s.newSiteShortName, 0, 13)," +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(1) AS TOTAL_ANALYSE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY s.id, s.newSiteShortName, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getPatientForDistrictWithSite(@Param("districtId") Long districtId , @Param("year") int year);

//Nombre de tests réalisés par site pour une region
    @Query(value = "SELECT s.id, SUBSTRING(s.new_site_short_name, 0, 13), " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND  " +
            " EXTRACT(YEAR FROM a.drcpt) = :year " +
            " GROUP BY s.id, s.new_site_short_name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
 List<Object[]> getTestBySiteForOneRegion(@Param("regionId") Long regionId , @Param("year") int year);

 //Nombre de patients testés réalisés par site pour une region
    @Query(value = "SELECT s.id, SUBSTRING(s.new_site_short_name, 0, 13), " +
            " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
            " FROM dashboard.region r " +
            " INNER JOIN dashboard.district d ON r.id = d.region_id" +
            " INNER JOIN dashboard.site s ON d.id = s.district_id " +
            " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
            " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
            " INNER join dashboard.test t on t.id = a.test_id " +
            " WHERE r.id = :regionId AND  " +
            " EXTRACT(YEAR FROM a.drcpt) = :year " +
            " GROUP BY s.id, s.new_site_short_name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
 List<Object[]> getPatientBySiteForOneRegion(@Param("regionId") Long regionId , @Param("year") int year);
//REGION

 //<1
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryMinusTwoForAllRegion(@Param("year") int year);


//1-4
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeCategoryBetweenOneAndFourForAllRegion(@Param("year") int year);


//5-9
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeCategoryBetweenFiveAndNineForAllRegion(@Param("year") int year);

//10-14
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND" + 
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> getTestedPatientByAgeCategoryBetweenTenAndFourteenForAllRegion(@Param("year") int year);


 // 15-19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientAgeCategoryBetweenFifteenAndNineteenForAllRegion(@Param("year") int year);

  //20-24
  @Query(value = "SELECT " +
  " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
  " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
  " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
  " FROM Region r " +
  " JOIN District d ON r.id = d.region.id " +
  " JOIN Site s ON d.id = s.district.id " +
  " JOIN Patient p ON s.id = p.site.id " +
  " JOIN Analysis a ON p.id = a.patient.id " +
  " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND" + 
  " EXTRACT(YEAR FROM a.drcpt) = :year " +
  " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
  List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyAndTwentyFourForAllRegion(@Param("year") int year);
 
  
 //25-29
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenTwentyFiveAndTwentyNineForAllRegion(@Param("year") int year);

 //30-34
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyAndThirtyFourForAllRegion(@Param("year") int year);

 //35-39
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenThirtyFourAndThirtyNineForAllRegion(@Param("year") int year);

 //40-44
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyAndFourtyFourForAllRegion(@Param("year") int year);
 
 //45-49
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 AND" + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryBetweenFourtyFiveAndFourtyNineForAllRegion(@Param("year") int year);



 //>50
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestedPatientByAgeCategoryGreaterThanFiftyForAllRegion(@Param("year") int year);
 

//DISTRICT

 // <1
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district1(@Param("districtId") Long districtId, @Param("year") int year);
 

 // 1-4
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district2(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 // 5-9
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district3(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 // 10-14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district4(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 // 15-19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district5(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 
 // 20-24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district6(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 // 25-29
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district7(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 
 // 30-34
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district8(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 // 35-39
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district9(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 
 
 // 40-44
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district10(@Param("districtId") Long districtId, @Param("year") int year);
 
  // 45-49
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district11(@Param("districtId") Long districtId, @Param("year") int year);
 
 
 // >50
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> district12(@Param("districtId") Long districtId, @Param("year") int year);
 
 // SITE

 
  
//<1
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site1(@Param("siteId") Long siteId, @Param("year") int year);


//1-4
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 1 AND 4 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site2(@Param("siteId") Long siteId, @Param("year") int year);




//5-9
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 5 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site3(@Param("siteId") Long siteId, @Param("year") int year);



//10-14
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site4(@Param("siteId") Long siteId, @Param("year") int year);


//15-19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site5(@Param("siteId") Long siteId, @Param("year") int year);

//20-24
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site6(@Param("siteId") Long siteId, @Param("year") int year);

//25-29
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 25 AND 29 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site7(@Param("siteId") Long siteId, @Param("year") int year);


//30-34
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 30 AND 34 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site8(@Param("siteId") Long siteId, @Param("year") int year);



//35-39
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 35 AND 39 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site9(@Param("siteId") Long siteId, @Param("year") int year);



//40-44
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 40 AND 44 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site10(@Param("siteId") Long siteId, @Param("year") int year);


//45-49
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 45 AND 49 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site11(@Param("siteId") Long siteId, @Param("year") int year);



//>50
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 50 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> site12(@Param("siteId") Long siteId, @Param("year") int year);

 //Nombre de tests réalisés pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, " +
 " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER join dashboard.test t on t.id = a.test_id " +
 " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
 " WHERE pt.id = :partnerId AND  " +
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getTestWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);


 //Nombre de patients testés pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, " +
 " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER join dashboard.test t on t.id = a.test_id " +
 " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
 " WHERE pt.id = :partnerId AND  " +
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getPatientWithDetailsByPartner(@Param("year") int year, @Param("partnerId") Long partnerId);


//Nombre de patients testés pour toutes les régions
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> patientOther(@Param("year") int year);

 // Nombre de patients testés par categorie d'âge pour un district choisi

//DISTRICT

 // <2
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtOne(@Param("districtId") Long districtId, @Param("year") int year);
 
 // 2-9
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtTwo(@Param("districtId") Long districtId, @Param("year") int year);
 
 // 10-14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtThree(@Param("districtId") Long districtId, @Param("year") int year);
 
 // 15-19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtFour(@Param("districtId") Long districtId, @Param("year") int year);
 
 // 20-24
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtFive(@Param("districtId") Long districtId, @Param("year") int year);
 
 //>25
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE d.id = :districtId AND " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " + 
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> districtSix(@Param("districtId") Long districtId, @Param("year") int year);

 // SITE
 
//<2
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 1 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteOne(@Param("siteId") Long siteId, @Param("year") int year);


//2-9
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteTwo(@Param("siteId") Long siteId, @Param("year") int year);

//10-14
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteThree(@Param("siteId") Long siteId, @Param("year") int year);

//15-19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteFour(@Param("siteId") Long siteId, @Param("year") int year);

//20-24
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteFive(@Param("siteId") Long siteId, @Param("year") int year);

//>25
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" JOIN Site s ON d.id = s.district.id " +
" JOIN Patient p ON s.id = p.site.id " +
" JOIN Analysis a ON p.id = a.patient.id " +
" WHERE s.id = :siteId AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 AND " + 
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]> siteSix(@Param("siteId") Long siteId, @Param("year") int year); 


//Nombre de tests pour toutes les regions 
   //<2
   @Query(value = "SELECT " +
   " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
   " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
   " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
   " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
   " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
   " FROM Region r " +
   " INNER JOIN District d ON r.id = d.region.id " +
   " INNER JOIN Site s ON d.id = s.district.id " +
   " INNER JOIN Patient p ON s.id = p.site.id " +
   " INNER JOIN Analysis a ON p.id = a.patient.id " +
   " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
   " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " +
   " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
   List<Object[]>testOne(@Param("year") int year);

   //2-9
   @Query(value = "SELECT " +
   " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
   " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
   " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
   " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
   " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
   " FROM Region r " +
   " INNER JOIN District d ON r.id = d.region.id " +
   " INNER JOIN Site s ON d.id = s.district.id " +
   " INNER JOIN Patient p ON s.id = p.site.id " +
   " INNER JOIN Analysis a ON p.id = a.patient.id " +
   " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
   " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " +
   " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
   List<Object[]>testTwo(@Param("year") int year);

   //10-14
   @Query(value = "SELECT " +
   " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
   " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
   " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
   " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
   " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
   " FROM Region r " +
   " INNER JOIN District d ON r.id = d.region.id " +
   " INNER JOIN Site s ON d.id = s.district.id " +
   " INNER JOIN Patient p ON s.id = p.site.id " +
   " INNER JOIN Analysis a ON p.id = a.patient.id " +
   " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
   " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14  " +
   " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
   List<Object[]>testThree(@Param("year") int year);

   //15-19
   @Query(value = "SELECT " +
   " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
   " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
   " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
   " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
   " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
   " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
   " FROM Region r " +
   " INNER JOIN District d ON r.id = d.region.id " +
   " INNER JOIN Site s ON d.id = s.district.id " +
   " INNER JOIN Patient p ON s.id = p.site.id " +
   " INNER JOIN Analysis a ON p.id = a.patient.id " +
   " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
   " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " +
   " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
   List<Object[]>testFour(@Param("year") int year);

    //20-24
    @Query(value = "SELECT " +
    " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
    " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
    " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
    " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
    " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
    " FROM Region r " +
    " INNER JOIN District d ON r.id = d.region.id " +
    " INNER JOIN Site s ON d.id = s.district.id " +
    " INNER JOIN Patient p ON s.id = p.site.id " +
    " INNER JOIN Analysis a ON p.id = a.patient.id " +
    " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
    " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " +
    " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]>testFive(@Param("year") int year);

    //>25
    @Query(value = "SELECT " +
    " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
    " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
    " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
    " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_1000, " +
    " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE_LL, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE_XXXX " +
    " FROM Region r " +
    " INNER JOIN District d ON r.id = d.region.id " +
    " INNER JOIN Site s ON d.id = s.district.id " +
    " INNER JOIN Patient p ON s.id = p.site.id " +
    " INNER JOIN Analysis a ON p.id = a.patient.id " +
    " WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
    " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25" +
    " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
    List<Object[]>testSix(@Param("year") int year);
    
//Nombre de patients testés par tranche d'âge pour toutes les regions
//<2
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientOne(@Param("year") int year);

//2-9
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientTwo(@Param("year") int year);

//10-14
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientThree(@Param("year") int year);

//15-19
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientFour(@Param("year") int year);


//20-24
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientFive(@Param("year") int year);

//>25
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND  " + 
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>patientSix(@Param("year") int year);






 


 



    

    
    














   // <2
    @Query(value = "SELECT " +
            " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
            " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
            " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
            " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
            " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
            " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
            " FROM Region r " +
            " INNER JOIN District d ON r.id = d.region.id " +
            " INNER JOIN Site s ON d.id = s.district.id " +
            " INNER JOIN Patient p ON s.id = p.site.id " +
            " INNER JOIN Analysis a ON p.id = a.patient.id " +
            " WHERE d.id = :districtId AND " + 
            " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
            " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " + 
            " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
            List<Object[]> districtSeven(@Param("districtId") Long districtId, @Param("year") int year);
            
            
//2-9
    @Query(value = "SELECT " +
    " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
    " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
    " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
    " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
    " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
    " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
    " FROM Region r " +
    " INNER JOIN District d ON r.id = d.region.id " +
    " INNER JOIN Site s ON d.id = s.district.id " +
    " INNER JOIN Patient p ON s.id = p.site.id " +
    " INNER JOIN Analysis a ON p.id = a.patient.id " +
    " WHERE  d.id = :districtId AND  " + 
    " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
    " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9 " +
    " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>districtEight(@Param("districtId") Long districtId , @Param("year") int year);


//10-14
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " INNER JOIN District d ON r.id = d.region.id " +
 " INNER JOIN Site s ON d.id = s.district.id " +
 " INNER JOIN Patient p ON s.id = p.site.id " +
 " INNER JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE  d.id = :districtId AND  " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>districtNine(@Param("districtId") Long districtId , @Param("year") int year);


//15-19
 @Query(value = "SELECT " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " INNER JOIN District d ON r.id = d.region.id " +
 " INNER JOIN Site s ON d.id = s.district.id " +
 " INNER JOIN Patient p ON s.id = p.site.id " +
 " INNER JOIN Analysis a ON p.id = a.patient.id " +
 " WHERE  d.id = :districtId AND  " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
 " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " +
 " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>districtTen(@Param("districtId") Long districtId , @Param("year") int year);


//20-24
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE  d.id = :districtId AND  " + 
" EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>districtEleven(@Param("districtId") Long districtId , @Param("year") int year);

//>25
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" WHERE  d.id = :districtId AND  " + 
" EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>districtTwelve(@Param("districtId") Long districtId , @Param("year") int year);

//<2
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2 " +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteSeven(@Param("siteId") Long siteId , @Param("year") int year);

//<2-9
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9" +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteEight(@Param("siteId") Long siteId , @Param("year") int year);

//<10-14
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14" +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteNine(@Param("siteId") Long siteId , @Param("year") int year);

//<15-19
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19" +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteTen(@Param("siteId") Long siteId , @Param("year") int year);

//<20-24
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24" +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteEleven(@Param("siteId") Long siteId , @Param("year") int year);



//>25
@Query(value = "SELECT " +
       " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
       " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
       " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
       " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
       " FROM Region r " +
       " INNER JOIN District d ON r.id = d.region.id " +
       " INNER JOIN Site s ON d.id = s.district.id " +
       " INNER JOIN Patient p ON s.id = p.site.id " +
       " INNER JOIN Analysis a ON p.id = a.patient.id " +
       " WHERE  s.id = :siteId AND  " + 
       " EXTRACT(YEAR FROM a.drcpt) = :year AND " +
       " FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " +
       " GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>siteTwelve(@Param("siteId") Long siteId , @Param("year") int year);



 //Nombre de tests réalisés par partenaire
 @Query(value = "SELECT pt.id, pt.name," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
 " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX " +
 " FROM Region r " +
 " INNER JOIN District d ON r.id = d.region.id " +
 " INNER JOIN Site s ON d.id = s.district.id " +
 " INNER JOIN Patient p ON s.id = p.site.id " +
 " INNER JOIN Analysis a ON p.id = a.patient.id " +
 " INNER JOIN Test t on t.id = a.test.id " +
 " INNER JOIN SitePartner sp on s.id = sp.site.id " +
 " INNER JOIN Partner pt on pt.id = sp.partner.id " +
 " WHERE EXTRACT(YEAR FROM a.drcpt) = :year " + 
 " GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]>getTestByPartner(@Param("year") int year);

//Nombre de patients testés par partenaire
@Query(value = "SELECT pt.id, pt.name, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getPatientByPartner(@Param("year") int year);

@Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date, pt.id, pt.name, " +
" st.label, " +
" COUNT(t.id) AS total_tests " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id " +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
" INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
" INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
" WHERE pt.id = :partnerId " +
" AND a.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
" AND st.label = 'Tube EDTA - Violet' " +
" GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label, pt.id, pt.name " +
" ORDER BY  a.drcpt ASC;", nativeQuery = true)
List<Object[]> getestByspecimenEDTAPlasma(@Param("partnerId") int partnerId);


@Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  pt.id, pt.name, " +
" st.label, " +
" COUNT(t.id) AS total_tests " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
" INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
" INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
" WHERE pt.id = :partnerId " +
" AND a.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
" AND st.label = 'DBS' " +
" GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label, pt.id, pt.name " +
" ORDER BY a.drcpt ASC;", nativeQuery = true)
List<Object[]> getestByspecimenDBS(@Param("partnerId") int partnerId);


@Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date, pt.id, pt.name, " +
" st.label, " +
" COUNT(t.id) AS total_tests " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
" INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
" INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
" WHERE pt.id = :partnerId " +
" AND a.drcpt BETWEEN '2023-01-01' AND CURRENT_DATE " +
" AND st.label = 'PSC' " +
" GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label, pt.id, pt.name  " +
" ORDER BY a.drcpt ASC;", nativeQuery = true)
List<Object[]> getestByspecimenPSC(@Param("partnerId") int partnerId); 

 //Nombre de patients testés pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, " +
 " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END )AS TOTAL_SUPPRIME, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
 " SUM(1) AS TOTAL_PATIENT, " +
 " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_1000, " +
 " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER JOIN dashboard.test t on t.id = a.test_id " +
 " INNER JOIN dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER JOIN dashboard.partner pt on pt.id = sp.partner_id " +
 " WHERE pt.id = :partnerId AND  " +
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getPatientForSpecificPartner(@Param("year") int year, @Param("partnerId") Long partnerId);

 //Nombre de tests réalisés pour un partenaire
 @Query(value = "SELECT pt.id, pt.name,  " +
 " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
 " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
 " SUM(1) AS TOTAL_TEST, " +
 " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result = 0 THEN 1 ELSE 0 END)AS TOTAL_VALIDE,  " +
 " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
 " ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
 " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER JOIN dashboard.test t on t.id = a.test_id " +
 " INNER JOIN dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER JOIN dashboard.partner pt on pt.id = sp.partner_id " +
 " WHERE pt.id = :partnerId AND  " +
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getTestForSpecificPartner(@Param("year") int year, @Param("partnerId") Long partnerId);


 //Nombre de patients testés par genre(Male) pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, p.gender," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " INNER JOIN District d ON r.id = d.region.id " +
 " INNER JOIN Site s ON d.id = s.district.id " +
 " INNER JOIN Patient p ON s.id = p.site.id " +
 " INNER JOIN Analysis a ON p.id = a.patient.id " +
 " INNER JOIN Test t on t.id = a.test.id " +
 " INNER JOIN SitePartner sp on s.id = sp.site.id " +
 " INNER JOIN Partner pt on pt.id = sp.partner.id " +
 " WHERE  pt.id = :partnerId AND p.gender = :sex AND  " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, p.gender, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getPatientForOnePartnerByMale(@Param("year") int year, @Param("partnerId") Long partnerId, @Param("sex") String sex);



 //Nombre de patients testés par genre(Female) pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, p.gender," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " INNER JOIN District d ON r.id = d.region.id " +
 " INNER JOIN Site s ON d.id = s.district.id " +
 " INNER JOIN Patient p ON s.id = p.site.id " +
 " INNER JOIN Analysis a ON p.id = a.patient.id " +
 " INNER JOIN Test t on t.id = a.test.id " +
 " INNER JOIN SitePartner sp on s.id = sp.site.id " +
 " INNER JOIN Partner pt on pt.id = sp.partner.id " +
 " WHERE  pt.id = :partnerId AND p.gender = :sex AND  " + 
 " EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, p.gender, EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getPatientForOnePartnerByFemale(@Param("year") int year, @Param("partnerId") Long partnerId, @Param("sex") String sex);


//Nombre de tests réalisés par genre(Male) pour un partenaire
@Query(value = "SELECT pt.id, pt.name, p.gender,  " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER JOIN dashboard.test t on t.id = a.test_id " +
" INNER JOIN dashboard.site_partner sp on s.id = sp.site_id " +
" INNER JOIN dashboard.partner pt on pt.id = sp.partner_id " +
" WHERE pt.id = :partnerId AND p.gender = :sex AND  " +
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY pt.id, pt.name, p.gender, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getTestForOnePartnerByMale(@Param("year") int year, @Param("partnerId") Long partnerId, @Param("sex") String sex);


//Nombre de tests réalisés par genre(Female) pour un partenaire
@Query(value = "SELECT pt.id, pt.name, p.gender,  " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER JOIN dashboard.test t on t.id = a.test_id " +
" INNER JOIN dashboard.site_partner sp on s.id = sp.site_id " +
" INNER JOIN dashboard.partner pt on pt.id = sp.partner_id " +
" WHERE pt.id = :partnerId AND p.gender = :sex AND  " +
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY pt.id, pt.name, p.gender, EXTRACT(YEAR FROM a.drcpt); ", nativeQuery = true)
List<Object[]> getTestForOnePartnerByFemale(@Param("year") int year, @Param("partnerId") Long partnerId, @Param("sex") String sex);



// Motif de le demande des tests
@Query(
        "SELECT pt.id, pt.name, " +
                "'CV contrôle sous ARV', " +
                "ROUND(SUM(CASE WHEN vl.name = 'CV contrôle sous ARV' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS cvControleSousARV, " +
                "'Autres', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS Autres, " +
                "'Echec clinique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec clinique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecClinique, " +
                "'Echec immunologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec immunologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecImmunologique, " +
                "'Echec virologique', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Echec virologique' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS echecVirologique, " +
                "'Autres (à préciser)', " +
                "ROUND(SUM(CASE WHEN vl.name = 'Autres (à préciser)' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS autres, " +
                "'', " +
                "ROUND(SUM(CASE WHEN vl.name = '' THEN 1 ELSE 0 END) * 100.0 / COUNT(a.id), 2) AS invalide " +
                "FROM Region r " +
                "JOIN District d ON r.id = d.region.id " +
                "JOIN Site s ON d.id = s.district.id " +
                "JOIN Patient p ON s.id = p.site.id " +
                "JOIN Analysis a ON p.id = a.patient.id " +
                "JOIN Test t ON a.test.id = t.id " +
                "JOIN Regimen reg ON a.regimen.id = reg.id " +
                "JOIN VlReason vl ON a.vlReason.id = vl.id " +
                "JOIN SitePartner sp on s.id = sp.site.id " +
                "JOIN Partner pt on pt.id = sp.partner.id " +
                "WHERE pt.id = :partnerId AND " +
                "EXTRACT(YEAR FROM a.drcpt) = :year " + 
                "GROUP BY pt.id, pt.name, EXTRACT(YEAR FROM a.drcpt) "
                )
List<Object[]> motifVlreasonByOnePartner(@Param("year") int year, @Param("partnerId") Long partnerId);



 //Nombre de tests réalisés par site pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, s.id, SUBSTRING(s.newSiteShortName, 0, 13)," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
 " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, s.id, s.newSiteShortName, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getTestBySiteForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);

 //Nombre de patients testés par site pour un partenaire
 @Query(value = "SELECT pt.id, pt.name, s.id, SUBSTRING(s.newSiteShortName, 0, 13)," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY pt.id, pt.name, s.id, s.newSiteShortName, EXTRACT(YEAR FROM a.drcpt)")
 List<Object[]> getPatientBySiteForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);

 //Nombre de tests réalisés par tranche d'âge pour un partenaire
   //CDC CI Notation
   @Query(value =  " SELECT part.name,  cdc_age_cat.label cdc_age_label, " +
   " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
   " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " + 
   " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL,  " +
   " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
   " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
   " ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
   " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
   " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
   " FROM dashboard.analysis a " +
   " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
   " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
   " JOIN dashboard.district dis on dis.id = s.district_id " +
   " JOIN dashboard.region reg on reg.id = dis.region_id " +
   " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
   " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
   " LEFT JOIN dashboard.partner part on part.id = site_part.partner_id " +
   " WHERE  part.id = :partnerId AND cdc_age_cat.type = 'CDC CI' AND EXTRACT(YEAR FROM a.drcpt) = :year " +	
   " GROUP BY part.name, cdc_age_cat.label, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true)
   List<Object[]> geTestByCategoryCDCForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);


//National notation
@Query(value = " SELECT part.name, nat_age_cat.label nat_age_label, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category nat_age_cat on nat_age_cat.id = a.age_national_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" LEFT JOIN dashboard.partner part on part.id = site_part.partner_id " +
" WHERE part.id = :partnerId and nat_age_cat.type = 'National' AND EXTRACT(YEAR FROM a.drcpt) = :year " +	
" GROUP BY part.name,nat_age_cat.label, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true)
List<Object[]> geTestByCategoryNationalForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);


//Nombre de patients testés par tranche d'âge

  //CDC CI notation
@Query(value =  " SELECT part.name, cdc_age_cat.label cdc_age_label, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" WHERE part.id = :partnerId  AND cdc_age_cat.type = 'CDC CI' AND EXTRACT(YEAR FROM a.drcpt) = :year " +	
" GROUP BY part.name,cdc_age_cat.label,EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getPatientByCategoryCdciForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);


  //National notation 
  @Query(value = " SELECT part.name, nat_age_cat.label nat_age_label, " + 
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
" FROM dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" left join dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category nat_age_cat on nat_age_cat.id = a.age_national_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" WHERE part.id = :partnerId AND nat_age_cat.type = 'National' AND EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY part.name,nat_age_cat.label,EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true)
List<Object[]> getPatientByCategoryNationalForOnePartner(@Param("partnerId") Long partnerId , @Param("year") int year);


//PARTENAIRE
           //REGIME THERAPEUTIQUE PAR PARTENAIRE
              //NOMBRE DE TESTS REALISES PAR REGIME THERAPEUTIQUE POUR TOUS LES PARTENAIRES

              @Query(value = "SELECT rg.name,  " +
              " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
              " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
              " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
              " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX " +
              " FROM Region r " +
              " JOIN District d ON r.id = d.region.id " +
              " JOIN Site s ON d.id = s.district.id " +
              " JOIN Patient p ON s.id = p.site.id " +
              " JOIN Analysis a ON p.id = a.patient.id " +
              " JOIN SitePartner sp on s.id = sp.site.id " +
              " JOIN Partner pt on pt.id = sp.partner.id " +
              " JOIN Regimen rg on rg.id = a.regimen.id " +
              " AND EXTRACT(YEAR FROM a.drcpt) = :year " +
              " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)" +
              " ORDER BY TOTAL_SUPERIEUR_OU_EGAL_1000 DESC, TOTAL_INFERIEUR_A_1000 DESC, TOTAL_INDETECTABLE_LL DESC, TOTAL_INVALIDE_XXXX DESC ")
              List<Object[]> getTestByRegimenForAllPartner(@Param("year") int year);

       //NOMBRE DE PATIENTS TESTES PAR REGIME THERAPEUTIQUE

 @Query(value = "SELECT rg.name," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " JOIN Regimen rg on rg.id = a.regimen.id " +
 " AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)" +
 " ORDER BY TOTAL_NON_SUPPRIME DESC, TOTAL_SUPPRIME DESC, POURCENTAGE_SUPPRIME DESC  ")
 List<Object[]> getPatientByRegimenForAllPartner(@Param("year") int year);

 //NOMBRE DE TESTS REALISES PAR REGIME THERAPEUTIQUE POUR UN PARTENAIRE

 @Query(value = "SELECT rg.name,  " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
 " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " JOIN Regimen rg on rg.id = a.regimen.id " +
 " WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)" +
 " ORDER BY TOTAL_SUPERIEUR_OU_EGAL_1000 DESC, TOTAL_INFERIEUR_A_1000 DESC, TOTAL_INDETECTABLE_LL DESC, TOTAL_INVALIDE_XXXX DESC ")
 List<Object[]> getTestByRegimenForOnePartner(@Param("partnerId") Long partnerId, @Param("year") int year);


 //NOMBRE DE PATIENTS TESTES PAR REGIME THERAPEUTIQUE POUR UN PARTENAIRE 
 @Query(value = "SELECT rg.name," +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
 " SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " JOIN Regimen rg on rg.id = a.regimen.id " +
 " WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)" +
 " ORDER BY TOTAL_NON_SUPPRIME DESC, TOTAL_SUPPRIME DESC, POURCENTAGE_SUPPRIME DESC ")
 List<Object[]> getPatientByRegimenForOnePartner(@Param("partnerId") Long partnerId, @Param("year") int year);

 //NOMBRE DE TESTS REALISES PAR SPECIMEN POUR UN REGIME CHOISI

  //DBS
@Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
" st.label, " +
" COUNT(t.id) AS total_tests " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
" INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
" INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
" WHERE st.label = 'DBS' AND a.regimen_id = :regimenId " +
" AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
" GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
" ORDER BY a.drcpt ASC;", nativeQuery = true)
List<Object[]> getestBySpecimenDBSForOneRegimen(@Param("regimenId") Long regimenId);

 //PSC
 @Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
 " st.label, " +
 " COUNT(t.id) AS total_tests " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER join dashboard.test t on t.id = a.test_id " +
 " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
 " INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
 " INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
 " INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
 " WHERE st.label = 'PSC' AND a.regimen_id = :regimenId " +
 " AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
 " GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
 " ORDER BY a.drcpt ASC;", nativeQuery = true)
 List<Object[]> getestBySpecimenPSCForOneRegimen(@Param("regimenId") Long regimenId);

  //EDTA PLASMA
  @Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
  " st.label, " +
  " COUNT(t.id) AS total_tests " +
  " FROM dashboard.region r " +
  " INNER JOIN dashboard.district d ON r.id = d.region_id" +
  " INNER JOIN dashboard.site s ON d.id = s.district_id " +
  " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
  " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
  " INNER join dashboard.test t on t.id = a.test_id " +
  " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
  " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
  " INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
  " INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
  " INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
  " WHERE st.label = 'Tube EDTA - Violet' AND a.regimen_id = :regimenId " +
  " AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
  " GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
  " ORDER BY a.drcpt ASC;", nativeQuery = true)
  List<Object[]> getestBySpecimenEdtaPlasmaForOneRegimen(@Param("regimenId") Long regimenId);

   //NOMBRE DE TESTS REALISES POUR UN REGIME THERAPEUTIQUE EN FONCTION DE L'ANNEE EN COURS
 @Query(value = "SELECT rg.name,  " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
 " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
 " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
 " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
 " SUM(1) AS TOTAL_TEST, " +
 " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult = 0 THEN 1 ELSE 0 END)AS TOTAL_VALIDE,  " +
 " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
 " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
 " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
 " FROM Region r " +
 " JOIN District d ON r.id = d.region.id " +
 " JOIN Site s ON d.id = s.district.id " +
 " JOIN Patient p ON s.id = p.site.id " +
 " JOIN Analysis a ON p.id = a.patient.id " +
 " JOIN SitePartner sp on s.id = sp.site.id " +
 " JOIN Partner pt on pt.id = sp.partner.id " +
 " JOIN Regimen rg on rg.id = a.regimen.id " +
 " WHERE a.regimen.id = :regimenId  AND EXTRACT(YEAR FROM a.drcpt) = :year " +
 " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)") 
 List<Object[]> getTestForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year);

   //NOMBRE DE TESTS REALISES PAR TRANCHE D'AGE POUR UN REGIME CHOISI
  //CDC CI notation - Masculin
@Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" WHERE a.regimen_id = :regimenId  AND  " +   
" cdc_age_cat.type = 'CDC CI' AND " +
" pat.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY pat.gender,cdc_age_cat.label, rg.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getTestByCDCMaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year, @Param("sex") String sex);

  //CDC CI notation - Feminin
@Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" WHERE a.regimen_id = :regimenId  AND  " +   
" cdc_age_cat.type = 'CDC CI' AND " +
" pat.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY pat.gender,cdc_age_cat.label, rg.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getTestByCdcFemaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year, @Param("sex") String sex);


//NOMBRE DE PATIENTS TESTES PAR TRANCHE D'AGE POUR UN REGIME CHOISI
  //CDC CI notation - Masculin
@Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" WHERE a.regimen_id = :regimenId  AND  " +   
" cdc_age_cat.type = 'CDC CI' AND " +
" pat.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY pat.gender,cdc_age_cat.label, rg.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getPatientByCDCMaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year, @Param("sex") String sex);

  //CDC CI notation - Feminin
  @Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
  " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
  " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE a.regimen_id = :regimenId  AND  " +   
  " cdc_age_cat.type = 'CDC CI' AND " +
  " pat.gender = :sex AND " +
  " EXTRACT(YEAR FROM a.drcpt) = :year  " +
  " GROUP BY pat.gender,cdc_age_cat.label, rg.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getPatientByCDCFemaleForOneRegimen(@Param("regimenId") Long regimenId, @Param("year") int year, @Param("sex") String sex);
  
//NOMBRE DE TESTS REALISES PAR SPECIMEN SUR UNE PERIODE POUR UN PARTENAIRE ET UN REGIME CHOISI

//DBS
@Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
" st.label, " +
" COUNT(t.id) AS total_tests " +
" FROM dashboard.region r " +
" INNER JOIN dashboard.district d ON r.id = d.region_id" +
" INNER JOIN dashboard.site s ON d.id = s.district_id " +
" INNER JOIN dashboard.patient p ON s.id = p.site_id" +
" INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
" INNER join dashboard.test t on t.id = a.test_id " +
" INNER join dashboard.site_partner sp on s.id = sp.site_id " +
" INNER join dashboard.partner pt on pt.id = sp.partner_id " +
" INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
" INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
" INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
" WHERE st.label = 'DBS' AND a.regimen_id = :regimenId AND pt.id = :partnerId " +
" AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
" GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
" ORDER BY a.drcpt ASC;", nativeQuery = true)
List<Object[]> getestBySpecimenDBSForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId );

 //PSC
 @Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
 " st.label, " +
 " COUNT(t.id) AS total_tests " +
 " FROM dashboard.region r " +
 " INNER JOIN dashboard.district d ON r.id = d.region_id" +
 " INNER JOIN dashboard.site s ON d.id = s.district_id " +
 " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
 " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
 " INNER join dashboard.test t on t.id = a.test_id " +
 " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
 " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
 " INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
 " INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
 " INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
 " WHERE st.label = 'PSC' AND a.regimen_id = :regimenId AND pt.id = :partnerId " +
 " AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
 " GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
 " ORDER BY a.drcpt ASC;", nativeQuery = true)
 List<Object[]> getestBySpecimenPSCForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId);

  //EDTA PLASMA
  @Query(value = " SELECT TO_CHAR(a.drcpt, 'Mon-YYYY') AS date,  " +
  " st.label, " +
  " COUNT(t.id) AS total_tests " +
  " FROM dashboard.region r " +
  " INNER JOIN dashboard.district d ON r.id = d.region_id" +
  " INNER JOIN dashboard.site s ON d.id = s.district_id " +
  " INNER JOIN dashboard.patient p ON s.id = p.site_id" +
  " INNER JOIN dashboard.analysis a ON p.id = a.patient_id " +
  " INNER join dashboard.test t on t.id = a.test_id " +
  " INNER join dashboard.site_partner sp on s.id = sp.site_id " +
  " INNER join dashboard.partner pt on pt.id = sp.partner_id " +
  " INNER JOIN dashboard.regimen rg ON a.regimen_id = rg.id " +
  " INNER JOIN dashboard.vl_reason vr ON a.vl_reason_id = vr.id " +
  " INNER JOIN dashboard.sample_type st ON a.sample_type_id = st.id " +
  " WHERE st.label = 'Tube EDTA - Violet' AND a.regimen_id = :regimenId AND pt.id = :partnerId " +
  " AND a.drcpt BETWEEN '2022-01-01' AND CURRENT_DATE " +
  " GROUP BY a.drcpt, TO_CHAR(a.drcpt, 'Mon-YYYY'), st.label  " +
  " ORDER BY a.drcpt ASC;", nativeQuery = true)
  List<Object[]> getestBySpecimenEdtaPlasmaForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId);

  //NOMBRE DE TESTS REALISES POUR UN REGIME THERAPEUTIQUE EN FONCTION DE L'ANNEE EN COURS POUR UN PARTENAIRE
  @Query(value = "SELECT rg.name,  " +
  " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
  " SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
  " SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
  " SUM(1) AS TOTAL_TEST, " +
  " SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult = 0 THEN 1 ELSE 0 END)AS TOTAL_VALIDE,  " +
  " ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
  " ROUND(SUM(CASE WHEN a.convertedResult < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
  " ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
  " ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
  " FROM Region r " +
  " JOIN District d ON r.id = d.region.id " +
  " JOIN Site s ON d.id = s.district.id " +
  " JOIN Patient p ON s.id = p.site.id " +
  " JOIN Analysis a ON p.id = a.patient.id " +
  " JOIN SitePartner sp on s.id = sp.site.id " +
  " JOIN Partner pt on pt.id = sp.partner.id " +
  " JOIN Regimen rg on rg.id = a.regimen.id " +
  " WHERE a.regimen.id = :regimenId  AND  " +
  " pt.id = :partnerId AND "  +
  " EXTRACT(YEAR FROM a.drcpt) = :year                                                                        " +
  " GROUP BY rg.name, EXTRACT(YEAR FROM a.drcpt)") 
  List<Object[]> getTestForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year);
 
//NOMBRE DE TESTS REALISES PAR TRANCHE D'AGE POUR UN REGIME CHOISI ET UN PARTENAIRE
  //CDC CI notation - Masculin
  @Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, part.name, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
  " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
  " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
  " ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
  " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
  " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE a.regimen_id = :regimenId  AND  " + 
  " part.id = :partnerId AND "  +
  " cdc_age_cat.type = 'CDC CI' AND " +
  " pat.gender = :sex AND " +
  " EXTRACT(YEAR FROM a.drcpt) = :year  " +
  " GROUP BY pat.gender,cdc_age_cat.label, rg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getTestByCDCMaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year, @Param("sex") String sex);
  
  //CDC CI notation - Feminin
  @Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, part.name, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
  " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
  " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPERIEUR_OU_EGAL_A_1000, " +
  " ROUND(SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INFERIEUR_A_1000, " +
  " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
  " ROUND(SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE a.regimen_id = :regimenId  AND  " +   
  " part.id = :partnerId AND "  +
  " cdc_age_cat.type = 'CDC CI' AND " +
  " pat.gender = :sex AND " +
  " EXTRACT(YEAR FROM a.drcpt) = :year  " +
  " GROUP BY pat.gender,cdc_age_cat.label, rg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getTestByCDCFemaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year, @Param("sex") String sex);
  
  
//NOMBRE DE PATIENTS TESTES PAR TRANCHE D'AGE POUR UN REGIME CHOISI ET UN PARTENAIRE
  //CDC CI notation - Masculin
@Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, part.name," +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" WHERE a.regimen_id = :regimenId  AND  " +  
" part.id = :partnerId AND "  +
" cdc_age_cat.type = 'CDC CI' AND " +
" pat.gender = :sex AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year  " +
" GROUP BY pat.gender,cdc_age_cat.label, rg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getPatientByCDCMaleForRegimenAndPartnerOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year, @Param("sex") String sex);

  //CDC CI notation - Feminin
  @Query(value =  " SELECT pat.gender, cdc_age_cat.label cdc_age_label, rg.name, part.name, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
  " ROUND(SUM(CASE WHEN a.converted_result = 0 THEN 1 WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
  " ROUND(SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE a.regimen_id = :regimenId  AND  " + 
  " part.id = :partnerId AND "  +  
  " cdc_age_cat.type = 'CDC CI' AND " +
  " pat.gender = :sex AND " +
  " EXTRACT(YEAR FROM a.drcpt) = :year  " +
  " GROUP BY pat.gender,cdc_age_cat.label, rg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getPatientByCDCFemaleForRegimenAndParterOne(@Param("regimenId") Long regimenId, @Param("partnerId") Long partnerId, @Param("year") int year, @Param("sex") String sex);
  
//STATISTIQUES DES ANALYSES PATIENTS PAR REGION ET PAR PARTENAIRE
  @Query(value =  " SELECT reg.id, reg.name, part.name,  " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
  " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
  " SUM(1) AS TOTAL_TEST, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 2 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 2 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 2 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(2) AS TOTAL_PATIENT " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE  EXTRACT(YEAR FROM a.drcpt) = :year " +
  " GROUP BY reg.id, reg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getTestAndPatientResultForPartnerAndRegion(@Param("year") int year);

  //STATISTIQUES DES ANALYSES PATIENTS PAR REGION ET POUR UN PARTENAIRE
  @Query(value =  " SELECT reg.id, reg.name, part.name,  " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
  " SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
  " SUM(1) AS TOTAL_TEST, " +
  " SUM(CASE WHEN a.converted_result >= 1000 THEN 2 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 2 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
  " SUM(CASE WHEN a.converted_result = 0 THEN 2 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
  " SUM(2) AS TOTAL_PATIENT " +
  " FROM  dashboard.analysis a " +
  " LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
  " LEFT JOIN dashboard.site s on s.id = pat.site_id " +
  " JOIN dashboard.district dis on dis.id = s.district_id " +
  " JOIN dashboard.region reg on reg.id = dis.region_id " +
  " LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
  " LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
  " JOIN dashboard.partner part on part.id = site_part.partner_id " +
  " JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
  " WHERE part.id = :partnerId AND " +
  " EXTRACT(YEAR FROM a.drcpt) = :year " +
  " GROUP BY reg.id, reg.name, part.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
  List<Object[]> getTestAndPatientResultForOnePartnerAndRegion(@Param("year") int year, @Param("partnerId") Long partnerId);
  

//STATISTIQUES DE PERFORMNCE POUR CHAQUE LABORATOIRE
@Query(value =  " SELECT l.id, l.name,   " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" SUM(1) AS TOTAL_TEST, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 2 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 2 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 2 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(2) AS TOTAL_PATIENT " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" JOIN dashboard.lab l on l.id = a.lab_id " +
" WHERE  EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY l.id, l.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getTestAndPatientResultForAllLab(@Param("year") int year);

//STATISTIQUES DE PERFORMNCE POUR UN LABORATOIRE LABORATOIRE
@Query(value =  " SELECT l.id, l.name,   " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 1 ELSE 0 END) AS TOTAL_SUPERIEUR_OU_EGAL_1000, " +
" SUM(CASE WHEN a.converted_result < 1000 THEN 1 ELSE 0 END ) AS TOTAL_INFERIEUR_A_1000, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.converted_result = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE_XXXX, " +
" SUM(1) AS TOTAL_TEST, " +
" SUM(CASE WHEN a.converted_result >= 1000 THEN 2 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result < 1000 AND a.converted_result > 0 THEN 2 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.converted_result = 0 THEN 2 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(2) AS TOTAL_PATIENT " +
" FROM  dashboard.analysis a " +
" LEFT JOIN dashboard.patient pat on pat.id = a.patient_id " +
" LEFT JOIN dashboard.site s on s.id = pat.site_id " +
" JOIN dashboard.district dis on dis.id = s.district_id " +
" JOIN dashboard.region reg on reg.id = dis.region_id " +
" LEFT JOIN dashboard.age_category cdc_age_cat on cdc_age_cat.id = a.age_cdc_id " +
" LEFT JOIN dashboard.site_partner site_part on site_part.site_id = s.id " +
" JOIN dashboard.partner part on part.id = site_part.partner_id " +
" JOIN dashboard.regimen rg on rg.id = a.regimen_id " +
" JOIN dashboard.lab l on l.id = a.lab_id " +
" WHERE  a.lab_id = :labId AND " +
" EXTRACT(YEAR FROM a.drcpt) = :year " +
" GROUP BY l.id, l.name, EXTRACT(YEAR FROM a.drcpt)", nativeQuery = true) 
List<Object[]> getTestAndPatientResultForOneLab(@Param("year") int year, @Param("labId") Long labId);








 //NOMBRE DE TESTS REALISES POUR UN REGIME CHOISI

 //NOMBRE DE TESTS REALISES PAR TRANCHE D'AGE POUR UN REGIME CHOISI

 //NOMBRE DE PATIENTS TESTES PAR TRANCHE D'GE POUR UN REGIME CHOISI







 /******************************************************************************************************************************* */
/* @Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>testCategorieOptimize(@Param("year") int year, @Param("partnerId") Long partnerId ); */

/* @Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE pt.id = :partnerId AND EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) < 2" +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestByCategoryAgeMinus2(@Param("year") int year, @Param("partnerId") Long partnerId ); */
/* 
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 2 AND 9  " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestByCategoryAgeBetweenTwoAndNine(@Param("year") int year);
 */

/* 
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 10 AND 14 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestBySpecificCategoryAgeBetweenTenAndFourteen(@Param("year") int year);
 */
/* 
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 15 AND 19 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestBySpecificCategoryAgeBetweenFifteenAndNineteen(@Param("year") int year);
 */
/* 
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) BETWEEN 20 AND 24 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestBySpecificCategoryAgeBetweenTwentyAndTwentyFour(@Param("year") int year);
 */
/* 
@Query(value = "SELECT " +
" SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) AS TOTAL_NON_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END ) AS TOTAL_SUPPRIME, " +
" SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) AS TOTAL_INDETECTABLE_LL, " +
" SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) AS TOTAL_INVALIDE, " +
" ROUND(SUM(CASE WHEN a.convertedResult >= 1000 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_NON_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 WHEN a.convertedResult < 1000 AND a.convertedResult > 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_SUPPRIME, " +
" ROUND(SUM(CASE WHEN a.convertedResult = 0 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INDETECTABLE, " +
" ROUND(SUM(CASE WHEN a.convertedResult = -1 THEN 1 ELSE 0 END) * 100.0 / SUM(1), 2) AS POURCENTAGE_INVALIDE " +
" FROM Region r " +
" INNER JOIN District d ON r.id = d.region.id " +
" INNER JOIN Site s ON d.id = s.district.id " +
" INNER JOIN Patient p ON s.id = p.site.id " +
" INNER JOIN Analysis a ON p.id = a.patient.id " +
" INNER JOIN AgeCategory ag ON ag.id = a.ageCategory.id " +
" WHERE EXTRACT(YEAR FROM a.drcpt) = :year AND " +
" FLOOR(EXTRACT(YEAR FROM CURRENT_DATE()) - EXTRACT(YEAR FROM p.birthDate)) > 25 " +
" GROUP BY EXTRACT(YEAR FROM a.drcpt)")
List<Object[]>getTestBySpecificCategoryAgeGreaterThanTwentyFive(@Param("year") int year);
 */



























 











}
