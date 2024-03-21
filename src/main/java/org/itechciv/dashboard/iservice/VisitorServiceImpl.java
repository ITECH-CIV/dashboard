package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Visitor;
import org.itechciv.dashboard.service.VisitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitorServiceImpl extends GenericServiceImpl<Visitor, Long> implements VisitorService {

}
