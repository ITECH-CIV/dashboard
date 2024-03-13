package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl extends GenericServiceImpl<Test, UUID> implements TestService {

}
