package com.spring.gucms.service;

import com.spring.gucms.dto.AuditTopicsInfoDTO;
import com.spring.gucms.entity.AuditTopicsInfo;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.repository.AuditTopicsInfoRepository;
import com.spring.gucms.repository.AuditTopicsRolesInfoRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@NoArgsConstructor
@Slf4j
public class AuditTopicsInfoService {
    private AuditTopicsInfoRepository auditTopicsInfoRepository;
    private AuditTopicsRolesInfoRepository auditTopicsRolesInfoRepository;

    @Autowired
    public AuditTopicsInfoService(AuditTopicsInfoRepository auditTopicsInfoRepository, AuditTopicsRolesInfoRepository auditTopicsRolesInfoRepository) {
        this.auditTopicsInfoRepository = auditTopicsInfoRepository;
        this.auditTopicsRolesInfoRepository = auditTopicsRolesInfoRepository;
    }

    public void addAuditTopicsInfo(AuditTopicsInfo auditTopicsInfo){
      //  AuditTopicsRolesInfo auditTopicsRolesInfo= auditTopicsRolesInfoRepository.findByInfoName("Naming");
        //System.out.println("kkkkk");
        //System.out.println(auditTopicsRolesInfo.getInfo_ID());
        /*Set<AuditTopicsRolesInfo>topicsRolesInfoSet = null;
        topicsRolesInfoSet.add(auditTopicsRolesInfo);*/
      //  System.out.println(auditTopicsInfo.getInfo_ids());
         auditTopicsInfoRepository.save(auditTopicsInfo);
    }
    public AuditTopicsInfo updateAuditTopicsInfo(AuditTopicsInfo auditTopicsInfo,long id){
        AuditTopicsInfo auditTopicsInfo1 = auditTopicsInfoRepository.findById(id).get();
        auditTopicsInfo1.setProblem_name(auditTopicsInfo.getProblem_name());
        Set<AuditTopicsInfo> auditSet =null;
        return auditTopicsInfoRepository.save(auditTopicsInfo);

    }
    public List<AuditTopicsInfo>getAllAuditTopicsInfo(){


        return auditTopicsInfoRepository.findAll();
    }
    public AuditTopicsInfo getAuditTopicsInfoById(long id){
        AuditTopicsInfo topicsInfo = auditTopicsInfoRepository.findById(id).get();
        System.out.println(topicsInfo.getInfo_ids());
        return topicsInfo;
    }
    public AuditTopicsInfoDTO getAuditTopicsInfoByIdInDTO(long id){
        AuditTopicsInfo topicsInfo = auditTopicsInfoRepository.findById(id).get();
        System.out.println(topicsInfo.getInfo_ids());
        AuditTopicsInfoDTO auditTopicsInfoDTO= convertToAuditTopicInfoDTO(topicsInfo);
        return auditTopicsInfoDTO;
    }
    public Boolean deleteAuditTopicsInfo(long id){
         auditTopicsInfoRepository.deleteById(id);
         return TRUE;

    }

    public List<AuditTopicsInfo> getAllTopicWithoutParent() {
        List<AuditTopicsInfo> topicsInfoWithoutParent = new ArrayList<>();
        List<AuditTopicsInfo>allTopic = getAllAuditTopicsInfo();
        for(AuditTopicsInfo auditTopicsInfo : allTopic){
            Iterator<AuditTopicsRolesInfo> iterate = auditTopicsInfo.getInfo_ids().iterator();
            if(iterate.hasNext()){
                if(iterate.next().getParent()==null){
                    topicsInfoWithoutParent.add(auditTopicsInfo);
                }
            }
        }
        return topicsInfoWithoutParent;
    }
    public AuditTopicsInfoDTO convertToAuditTopicInfoDTO(AuditTopicsInfo topicsInfo){
        AuditTopicsInfoDTO topicsInfoDTO = new AuditTopicsInfoDTO();
        topicsInfoDTO.setTopic_id(topicsInfo.getTopic_id());
        topicsInfoDTO.setAudit(topicsInfo.getAudit());
        topicsInfoDTO.setCreated_date(topicsInfo.getCreated_date());
        topicsInfoDTO.setInvestigate_recommendation(topicsInfo.getInvestigate_recommendation());
        topicsInfoDTO.setProblem_name(topicsInfo.getProblem_name());
        topicsInfoDTO.setLast_updated_user_id(topicsInfo.getLast_updated_user_id());
        topicsInfoDTO.setCreated_user_id(topicsInfo.getCreated_user_id());
        topicsInfoDTO.setLast_updated_date(topicsInfo.getLast_updated_date());
        topicsInfoDTO.setInvestigate_Notes(topicsInfo.getInvestigate_Notes());
        for(AuditTopicsRolesInfo audit : topicsInfo.getInfo_ids())
            topicsInfoDTO.getInfo_ids().add(audit.getInfoName());
        return topicsInfoDTO;
    }
}
