package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionServiceImpl extends GenericServiceImpl<Region, UUID> implements RegionService {

}
