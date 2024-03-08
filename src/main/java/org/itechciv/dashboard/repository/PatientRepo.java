package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface PatientRepo extends JpaRepository<Patient,Integer> {

    //requete jpql
    @Query("SELECT p.gender, COUNT(p) FROM Patient p JOIN p.analyses a GROUP BY p.gender")
    Map<String, Long> patientByGender();
}
