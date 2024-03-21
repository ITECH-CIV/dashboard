package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.SampleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleTypeRepository extends JpaRepository<SampleType, Long> {

}
