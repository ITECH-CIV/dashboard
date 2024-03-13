package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.service.FacilitysService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacilitysServiceImpl extends GenericServiceImpl<Facilitys, UUID> implements FacilitysService{

}
