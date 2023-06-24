package com.spring.gucms.service;

import com.spring.gucms.dto.TopicsDTO;
import com.spring.gucms.entity.Topics;
import com.spring.gucms.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService {
    @Autowired
    private TopicsRepository topicsRepository;

    public List<TopicsDTO> getAllTopics() {
        return ((List<Topics>) topicsRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

     TopicsDTO convertDataIntoDTO(Topics topics) {

         TopicsDTO dto = new TopicsDTO();

        dto.setInfo_ID(topics.getInfo_ID());
        dto.setInfo_Name(topics.getInfoName());

        Topics topic = topics.getParent();

        if(topics.getParent()==null){
            dto.setParent(0);
        }else{
            dto.setParent(topic.getInfo_ID());
        }
        return dto;
    }
}