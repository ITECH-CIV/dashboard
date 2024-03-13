package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.PageVisited;
import org.itechciv.dashboard.service.PageVisitedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PageVisitedServiceImpl extends GenericServiceImpl<PageVisited, UUID> implements PageVisitedService {

}
