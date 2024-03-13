package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.AgeCategory;
import org.itechciv.dashboard.service.AgeCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgeCategoryServiceImpl extends GenericServiceImpl<AgeCategory, UUID> implements AgeCategoryService {

}
