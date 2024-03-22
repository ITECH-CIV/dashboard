package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.AgeCategoryService;
import org.itechciv.dashboard.model.AgeCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgeCategoryServiceImpl extends GenericServiceImpl<AgeCategory, Long> implements AgeCategoryService {

}
