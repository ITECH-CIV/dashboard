package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.DistrictService;
import org.itechciv.dashboard.model.District;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl extends GenericServiceImpl<District, Long> implements DistrictService{

}
