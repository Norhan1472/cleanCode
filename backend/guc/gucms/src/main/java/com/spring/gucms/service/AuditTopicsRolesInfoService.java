package com.spring.gucms.service;

import com.spring.gucms.dto.AuditTopicRolesDTO;
import com.spring.gucms.entity.AuditTopicsInfo;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.repository.AuditTopicsRolesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class AuditTopicsRolesInfoService {


    private AuditTopicsRolesInfoRepository auditTopicsRolesInfoRepository;
    private MappingService mapService;
    @Autowired
    public AuditTopicsRolesInfoService(AuditTopicsRolesInfoRepository auditTopicsRolesInfoRepository, MappingService mapService) {
        this.auditTopicsRolesInfoRepository = auditTopicsRolesInfoRepository;
        this.mapService = mapService;
    }

    @Transactional
    public AuditTopicsRolesInfo updateAuditTopicsRolesInfo(Long id, AuditTopicsRolesInfo auditTopicsRolesInfo) {
        AuditTopicsRolesInfo existingAuditTopicsRolesInfo = auditTopicsRolesInfoRepository.findById(auditTopicsRolesInfo.getInfo_ID())
                .orElseThrow(() -> new EntityNotFoundException("Audit Topics Roles Info not found with id: " + auditTopicsRolesInfo.getInfo_ID()));

        existingAuditTopicsRolesInfo.setParent(auditTopicsRolesInfo.getParent());
        existingAuditTopicsRolesInfo.setInfoName(auditTopicsRolesInfo.getInfoName());
        existingAuditTopicsRolesInfo.setInfo_Desc(auditTopicsRolesInfo.getInfo_Desc());
        existingAuditTopicsRolesInfo.setLast_updated_date(new Date());
        existingAuditTopicsRolesInfo.setCreated_date(existingAuditTopicsRolesInfo.getCreated_date());
        existingAuditTopicsRolesInfo.setCreated_user_id(auditTopicsRolesInfo.getCreated_user_id());
        existingAuditTopicsRolesInfo.setLast_updated_user_id(auditTopicsRolesInfo.getLast_updated_user_id());

        return auditTopicsRolesInfoRepository.save(existingAuditTopicsRolesInfo);
    }

    public AuditTopicsRolesInfo saveOrUpdateAuditTopicsRolesInfo(AuditTopicsRolesInfo auditTopicsRolesInfo) {
        AuditTopicRolesDTO auditTopicRolesDTO = new AuditTopicRolesDTO();
        auditTopicRolesDTO.setInfo_ID(auditTopicRolesDTO.getInfo_ID());
        if(auditTopicsRolesInfo.getParent()==null){
            auditTopicRolesDTO.setParent(0);
        }else{
            auditTopicRolesDTO.setParent(auditTopicsRolesInfo.getParent().getInfo_ID());
        }
        auditTopicRolesDTO.setInfo_Name(auditTopicsRolesInfo.getInfoName());
        auditTopicRolesDTO.setInfo_Desc(auditTopicsRolesInfo.getInfo_Desc());
        //auditTopicRolesDTO;
        return auditTopicsRolesInfoRepository.save(auditTopicsRolesInfo);
    }

    public Boolean deleteAuditTopicsRolesInfoById(Long id) {
         auditTopicsRolesInfoRepository.deleteById(id);
         return TRUE;

    }

    public AuditTopicsRolesInfo findAuditTopicsRolesInfoById(Long id) {
        return auditTopicsRolesInfoRepository.findById(id).get();
    }

    public List<AuditTopicRolesDTO> findAllAuditTopicsRolesInfo() {
        List <AuditTopicRolesDTO> allAudits = mapService.getAllAudit();

        return allAudits;
    }

    public List<AuditTopicRolesDTO> getAllTopicWithoutParent() {
        List<AuditTopicRolesDTO>auditTopicsRolesInfoWithoutParent = new ArrayList<>();
        List<AuditTopicRolesDTO>allTopicsInfo=findAllAuditTopicsRolesInfo();
        for(AuditTopicRolesDTO auditTopicsRolesInfo :allTopicsInfo){
            if(auditTopicsRolesInfo.getParent()==0){
                auditTopicsRolesInfoWithoutParent.add(auditTopicsRolesInfo);
            }
        }

        return auditTopicsRolesInfoWithoutParent;
    }
    public AuditTopicRolesDTO findByInfoName(String infoName){
        AuditTopicsRolesInfo audit = auditTopicsRolesInfoRepository.findByInfoName(infoName);
        AuditTopicRolesDTO dto = mapService.convertDataIntoDTO(audit);
        return dto;
    }
}

