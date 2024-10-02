package com.pavan.samplecrud.repository;

import com.pavan.samplecrud.entity.APIAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIAuditRepository extends CrudRepository<APIAudit, Integer> {
}
