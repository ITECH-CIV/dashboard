package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.PageVisitedService;
import org.itechciv.dashboard.model.PageVisited;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PageVisitedServiceImpl extends GenericServiceImpl<PageVisited, Long> implements PageVisitedService {

}
