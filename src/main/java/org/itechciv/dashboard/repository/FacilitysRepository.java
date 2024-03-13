package org.itechciv.dashboard.repository;

import java.util.UUID;

import org.itechciv.dashboard.model.Facilitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitysRepository extends JpaRepository<Facilitys, UUID> {

}
