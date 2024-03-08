package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository <Test,Integer> {
}
