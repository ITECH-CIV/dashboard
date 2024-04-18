package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.PartnerService;
import org.itechciv.dashboard.model.Partner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartnerServiceImpl  extends GenericServiceImpl<Partner, Long> implements PartnerService  {

}
