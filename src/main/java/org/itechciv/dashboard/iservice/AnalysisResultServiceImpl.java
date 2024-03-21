package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.AnalysisResult;
import org.itechciv.dashboard.service.AnalysisResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnalysisResultServiceImpl extends GenericServiceImpl<AnalysisResult, Long> implements AnalysisResultService  {

}
