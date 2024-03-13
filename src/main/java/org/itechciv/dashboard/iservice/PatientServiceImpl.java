package org.itechciv.dashboard.iservice;

import java.util.UUID;

import org.itechciv.dashboard.model.Patient;
import org.itechciv.dashboard.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl extends GenericServiceImpl<Patient, UUID> implements PatientService {

}
