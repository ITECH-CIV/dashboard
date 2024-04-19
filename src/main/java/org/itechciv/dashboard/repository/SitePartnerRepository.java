package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.SitePartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitePartnerRepository extends JpaRepository<SitePartner, Long> {

}
