package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Analysis;
import org.itechciv.dashboard.service.AnalysisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnalysisServiceImpl extends GenericServiceImpl<Analysis, Long> implements AnalysisService {

}
