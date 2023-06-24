package com.spring.gucms.repository;

import com.spring.gucms.entity.AuditationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditationInfoRepository extends JpaRepository<AuditationInfo, Long> {
}