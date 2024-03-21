package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

}
