package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepo extends JpaRepository <Visitor,Integer>{
}
