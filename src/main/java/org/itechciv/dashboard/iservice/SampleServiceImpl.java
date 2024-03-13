package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.service.SampleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleServiceImpl extends GenericServiceImpl<Sample, UUID> implements SampleService {

}