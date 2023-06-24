package com.spring.gucms.controller;

import com.spring.gucms.dto.TopicsDTO;
import com.spring.gucms.entity.Topics;
import com.spring.gucms.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TopicsController {

    @Autowired
    private TopicService topicService;
    @GetMapping("/getAllTopic")
    public List<TopicsDTO> getAllTopic() {
        return topicService.findAlltopics();
    }

    @GetMapping("/topic/getTopicById/{id}")
    public ResponseEntity<TopicsDTO> getTopicById(@PathVariable(value = "id") Long id) {
        System.out.println("llllll");
        TopicsDTO topic = topicService.findTopicsById(id);
        if (topic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(topic);
    }

    @PostMapping("/addTopic")
    public Topics createTopic(@RequestBody Topics topic) {
        return topicService.saveOrUpdateTopics(topic);
    }

    @PutMapping("/updateTopic/{id}")
    public ResponseEntity<Topics> updateTopic(@PathVariable(value = "id") Long id, @RequestBody Topics topic) {

        Topics updatedTopic = topicService.updateTopic(id, topic);
        if (updatedTopic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTopic);
    }
    @GetMapping("/getAllTopicWithoutParent")
    public List<TopicsDTO>getAllTopicWithoutParent(){
        return topicService.getAllTopicWithoutParent();
    }
    @GetMapping("findByInfoName/{infoName}")
    public TopicsDTO findByInfoName(@PathVariable String infoName){
        System.out.println(infoName);
        return topicService.findByInfoName(infoName);
    }
    @DeleteMapping("/deleteById/{topicId}")
    public Boolean deleteTopic(@PathVariable long topicId){
        return topicService.deleteTopicsById(topicId);
    }


}
