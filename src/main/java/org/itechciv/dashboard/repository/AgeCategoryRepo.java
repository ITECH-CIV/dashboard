package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeCategoryRepo extends JpaRepository<AgeCategory, Integer> {
}
