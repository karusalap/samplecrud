package com.pavan.samplecrud.service.impl;

import com.pavan.samplecrud.controller.EmployeeServiceController;
import com.pavan.samplecrud.entity.APIAudit;
import com.pavan.samplecrud.repository.APIAuditRepository;
import com.pavan.samplecrud.service.APIAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class APIAuditServiceImpl implements APIAuditService {

    @Autowired
    private APIAuditRepository apiAuditRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceController.class);

    @Override
    @Async
    public void createAudit(APIAudit apiAudit) {
        logger.info("Create Audit started");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        apiAuditRepository.save(apiAudit);
    }

}
