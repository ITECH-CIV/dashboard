package org.itechciv.dashboard.repository;

import java.util.UUID;

import org.itechciv.dashboard.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
