package com.spring.gucms.repository;

import com.spring.gucms.entity.AuditTopicsRolesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTopicsRolesInfoRepository extends JpaRepository<AuditTopicsRolesInfo, Long> {
    public AuditTopicsRolesInfo findByInfoName(String infoName);

}