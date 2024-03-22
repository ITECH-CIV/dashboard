package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.AnalysisService;
import org.itechciv.dashboard.model.Analysis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnalysisServiceImpl extends GenericServiceImpl<Analysis, Long> implements AnalysisService {

}
