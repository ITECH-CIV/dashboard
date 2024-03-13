package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.service.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService{

}
