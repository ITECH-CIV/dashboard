package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepo extends JpaRepository<Sample,Integer> {
}
