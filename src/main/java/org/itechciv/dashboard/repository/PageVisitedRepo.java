package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.PageVisited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageVisitedRepo extends JpaRepository<PageVisited, Integer> {
}
