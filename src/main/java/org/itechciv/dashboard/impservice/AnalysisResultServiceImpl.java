package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.AnalysisResultService;
import org.itechciv.dashboard.model.AnalysisResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnalysisResultServiceImpl extends GenericServiceImpl<AnalysisResult, Long> implements AnalysisResultService  {

}
