package com.spring.gucms.repository;

import com.spring.gucms.entity.AuditTopicsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTopicsInfoRepository extends JpaRepository<AuditTopicsInfo,Long> {
}
