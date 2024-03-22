package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.VisitorService;
import org.itechciv.dashboard.model.Visitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitorServiceImpl extends GenericServiceImpl<Visitor, Long> implements VisitorService {

}
