package com.spring.gucms.service;

import com.spring.gucms.dto.AuditTopicRolesDTO;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.repository.AuditTopicsRolesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService {
    @Autowired
    private AuditTopicsRolesInfoRepository auditTopicsRolesInfoRepository;

    public List<AuditTopicRolesDTO> getAllAudit() {
        return ((List<AuditTopicsRolesInfo>) auditTopicsRolesInfoRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

     AuditTopicRolesDTO convertDataIntoDTO(AuditTopicsRolesInfo auditTopicsRolesInfo) {

        AuditTopicRolesDTO dto = new AuditTopicRolesDTO();

        dto.setInfo_ID(auditTopicsRolesInfo.getInfo_ID());
        dto.setInfo_Name(auditTopicsRolesInfo.getInfoName());

        AuditTopicsRolesInfo audit = auditTopicsRolesInfo.getParent();

        if(auditTopicsRolesInfo.getParent()==null){
            dto.setParent(0);
        }else{
            dto.setParent(audit.getInfo_ID());
        }
        return dto;
    }
}