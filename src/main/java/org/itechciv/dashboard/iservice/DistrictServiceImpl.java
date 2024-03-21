package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.service.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl extends GenericServiceImpl<District, Long> implements DistrictService{

}
