package com.spring.gucms.service;

import com.spring.gucms.entity.AuditationInfo;
import com.spring.gucms.repository.AuditationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditationInfoService {



    private AuditationInfoRepository auditationInfoRepository;

    @Autowired
    public AuditationInfoService(AuditationInfoRepository auditationInfoRepository) {
        this.auditationInfoRepository = auditationInfoRepository;
    }

    public List<AuditationInfo> getAllAuditationInfo() {
        return auditationInfoRepository.findAll();
    }

    public Optional<AuditationInfo> getAuditationInfoById(Long id) {
        return auditationInfoRepository.findById(id);
    }

    public AuditationInfo createAuditationInfo(AuditationInfo auditationInfo) {
        return auditationInfoRepository.save(auditationInfo);
    }

    public AuditationInfo updateAuditationInfo(Long id, AuditationInfo auditationInfo) {
        Optional<AuditationInfo> existingAuditationInfo = auditationInfoRepository.findById(id);

        if (existingAuditationInfo.isPresent()) {
            AuditationInfo updatedAuditationInfo = existingAuditationInfo.get();
            updatedAuditationInfo.setFileName(auditationInfo.getFileName());
            updatedAuditationInfo.setFileType(auditationInfo.getFileType());
            updatedAuditationInfo.setStartDate(auditationInfo.getStartDate());
            updatedAuditationInfo.setEndDate(auditationInfo.getEndDate());
            updatedAuditationInfo.setFolder_id(auditationInfo.getFolder_id());
            updatedAuditationInfo.setCreated_date(auditationInfo.getCreated_date());
            updatedAuditationInfo.setCreated_user_id(auditationInfo.getCreated_user_id());
            updatedAuditationInfo.setLast_updated_date(auditationInfo.getLast_updated_date());
            updatedAuditationInfo.setLast_updated_user_id(auditationInfo.getLast_updated_user_id());

            return auditationInfoRepository.save(updatedAuditationInfo);
        } else {
            return null;
        }
    }

    public boolean deleteAuditationInfoById(Long id) {
        Optional<AuditationInfo> existingAuditationInfo = auditationInfoRepository.findById(id);

        if (existingAuditationInfo.isPresent()) {
            auditationInfoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
