package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.DietService;
import org.itechciv.dashboard.model.Diet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DietServiceImpl extends GenericServiceImpl<Diet, Long> implements DietService {

}
