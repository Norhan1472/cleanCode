package com.spring.gucms.service;

import com.spring.gucms.dto.TopicsDTO;
import com.spring.gucms.entity.Topics;

import com.spring.gucms.repository.TopicsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class TopicService {

    private TopicsRepository topicsRepository;
    private MappingService mapService;
    @Autowired
    public TopicService(TopicsRepository topicsRepository, MappingService mapService) {
        this.topicsRepository = topicsRepository;
        this.mapService = mapService;
    }

    @Transactional
    public Topics updateTopic(Long id, Topics topics) {
        Topics existingTopics = topicsRepository.findById(topics.getInfo_ID())
                .orElseThrow(() -> new EntityNotFoundException("Audit Topics Roles Info not found with id: " + topics.getInfo_ID()));

        existingTopics.setParent(topics.getParent());
        existingTopics.setInfoName(topics.getInfoName());
        existingTopics.setInfo_Desc(topics.getInfo_Desc());
        existingTopics.setLast_updated_date(LocalDateTime.now());
        existingTopics.setCreated_date(existingTopics.getCreated_date());
        existingTopics.setCreated_user_id(topics.getCreated_user_id());
        existingTopics.setLast_updated_user_id(topics.getLast_updated_user_id());

        return topicsRepository.save(existingTopics);
    }

    public Topics saveOrUpdateTopics(Topics topics) {
        TopicsDTO topicsDTO = new TopicsDTO();
        topicsDTO.setInfo_ID(topicsDTO.getInfo_ID());
        if(topics.getParent()==null){
            topicsDTO.setParent(0);
        }else{
            topicsDTO.setParent(topics.getParent().getInfo_ID());
        }
        topicsDTO.setInfo_Name(topics.getInfoName());
        topicsDTO.setInfo_Desc(topics.getInfo_Desc());
        //auditTopicRolesDTO;
        return topicsRepository.save(topics);
    }

    public Boolean deleteTopicsById(Long id) {
         topicsRepository.deleteById(id);
         return TRUE;

    }

    public TopicsDTO findTopicsById(Long id) {
        System.out.println("helllllooooo");
        System.out.println(id);
        Topics topic = topicsRepository.findById(id).get();
        System.out.println("helllllooooo");
        System.out.println(topic.getInfo_ID());
        TopicsDTO dto = mapService.convertDataIntoDTO(topic);
        return dto;
    }

    public List<TopicsDTO> findAlltopics() {
        List <TopicsDTO> allTopics = mapService.getAllTopics();

        return allTopics;
    }

    public List<TopicsDTO> getAllTopicWithoutParent() {
        List<TopicsDTO>topicsWithoutParent = new ArrayList<>();
        List<TopicsDTO>allTopicsInfo=findAlltopics();
        for(TopicsDTO topics :allTopicsInfo){
            if(topics.getParent()==0){
                topicsWithoutParent.add(topics);
            }
        }

        return topicsWithoutParent;
    }
    public TopicsDTO findByInfoName(String infoName){
        Topics topic = topicsRepository.findByInfoName(infoName);
        TopicsDTO dto = mapService.convertDataIntoDTO(topic);
        return dto;
    }
}

