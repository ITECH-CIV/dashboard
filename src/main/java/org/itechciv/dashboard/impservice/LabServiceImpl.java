package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.LabService;
import org.itechciv.dashboard.iservice.RegionService;
import org.itechciv.dashboard.model.Lab;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabServiceImpl extends GenericServiceImpl<Lab, Long> implements LabService {

}
