package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.service.SampleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleTypeServiceImpl extends GenericServiceImpl<SampleType, Long> implements SampleTypeService {

}
