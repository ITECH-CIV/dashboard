package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.RegionService;
import org.itechciv.dashboard.model.Region;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionServiceImpl extends GenericServiceImpl<Region, Long> implements RegionService {

}
