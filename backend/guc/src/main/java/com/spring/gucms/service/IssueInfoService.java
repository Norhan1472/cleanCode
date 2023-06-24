package com.spring.gucms.service;

import com.spring.gucms.dto.IssueInfoDTO;
import com.spring.gucms.entity.IssueInfo;
import com.spring.gucms.entity.Topics;
import com.spring.gucms.entity.Utils;
import com.spring.gucms.repository.IssueInfoRepository;
import com.spring.gucms.repository.TopicsRepository;
import com.spring.gucms.repository.UtilsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@NoArgsConstructor
@Slf4j
//@AllArgsConstructor
public class IssueInfoService {
    private IssueInfoRepository issueInfoRepository;
    /*
    private TopicsRepository topicsRepository;
    private UtilsRepository utilsRepository;
    private UtilsService utilsService;*/
    @Autowired
    public IssueInfoService(IssueInfoRepository issueInfoRepository) {
        this.issueInfoRepository = issueInfoRepository;
    }

    public IssueInfo addIssueInfo(IssueInfo issueInfo){
      //  AuditTopicsRolesInfo auditTopicsRolesInfo= auditTopicsRolesInfoRepository.findByInfoName("Naming");
        //System.out.println("kkkkk");
        //System.out.println(auditTopicsRolesInfo.getInfo_ID());
        /*Set<AuditTopicsRolesInfo>topicsRolesInfoSet = null;
        topicsRolesInfoSet.add(auditTopicsRolesInfo);*/
      //  System.out.println(IssueInfo.getInfo_ids());
         return issueInfoRepository.save(issueInfo);
    }
    public IssueInfo updateIssueInfo(IssueInfo issueInfo,long id){
        IssueInfo issueInfo1 = issueInfoRepository.findById(id).get();
        issueInfo1.setProblem_name(issueInfo.getProblem_name());
        Set<IssueInfo> IssueSet =null;
        return issueInfoRepository.save(issueInfo1);

    }
    public List<IssueInfo>getAllIssueInfo(){
        return issueInfoRepository.findAll();
    }
    public IssueInfo getIssueInfoById(long id){
        return issueInfoRepository.findById(id).get();
    }
    public Boolean deleteIssueInfo(long id){
       // IssueInfo issue = getIssueInfoById(id);

         return TRUE;

    }

    public List<IssueInfo> getAllTopicWithoutParent() {
        List<IssueInfo> topicsInfoWithoutParent = new ArrayList<>();
        List<IssueInfo>allTopic = getAllIssueInfo();
        for(IssueInfo issueInfo : allTopic){
            Iterator<Topics> iterate = issueInfo.getTopics_ids().iterator();
            if(iterate.hasNext()){
                if(iterate.next().getParent()==null){
                    topicsInfoWithoutParent.add(issueInfo);
                }
            }
        }
        return topicsInfoWithoutParent;
    }
    public List<IssueInfoDTO>getAllIssueInfoDto(){
        List<IssueInfo>data = issueInfoRepository.findAll();
        List<IssueInfoDTO>dataDto = new ArrayList<>();
        System.out.println(data.size());
        for(int i=0;i<data.size();i++){
            IssueInfoDTO object = new IssueInfoDTO();
            object.setTopic_id(data.get(i).getTopic_id());
            object.setProblem_name(data.get(i).getProblem_name());
            object.setInvestigate_Notes(data.get(i).getInvestigate_Notes());
            object.setInvestigate_recommendation(data.get(i).getInvestigate_recommendation());
            object.setCreated_date(data.get(i).getCreated_date());
            object.setLast_updated_date(data.get(i).getLast_updated_date());
            Set<Topics>data2 = data.get(i).getTopics_ids();
           // Set<String>object2 = new HashSet<>();
            for(Topics nn :data2){
                object.getInfoName().add(nn.getInfoName());
            }
            dataDto.add(object);
        }
        for(int i=0;i<dataDto.size();i++){
            System.out.println(dataDto.get(i).getProblem_name());
        }
        return dataDto;
    }
}
