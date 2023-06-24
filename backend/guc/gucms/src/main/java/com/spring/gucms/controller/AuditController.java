package com.spring.gucms.controller;

import com.spring.gucms.dto.AuditTopicRolesDTO;
import com.spring.gucms.entity.AuditRoles;
import com.spring.gucms.entity.AuditTopicsInfo;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.entity.AuditationInfo;
import com.spring.gucms.service.AuditRolesService;
import com.spring.gucms.service.AuditTopicsInfoService;
import com.spring.gucms.service.AuditTopicsRolesInfoService;
import com.spring.gucms.service.AuditationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AuditController {
    @Autowired
    private AuditTopicsRolesInfoService auditTopicsRolesInfoService;

    @Autowired
    private AuditationInfoService auditationInfoService;

    @Autowired
    private AuditTopicsInfoService auditTopicsInfoService;

    @Autowired
    private AuditRolesService auditRolesService;

    // CRUD operations for AuditTopicsRolesInfo
    @GetMapping("/audit-topics-roles-info")
    public List<AuditTopicRolesDTO> getAllAuditTopicsRolesInfo() {
        return auditTopicsRolesInfoService.findAllAuditTopicsRolesInfo();
    }

    @GetMapping("/audit-topics-roles-info/getAuditTopicsRolesInfoById/{id}")
    public ResponseEntity<AuditTopicsRolesInfo> getAuditTopicsRolesInfoById(@PathVariable(value = "id") Long id) {
        System.out.println("llllll");
        AuditTopicsRolesInfo auditTopicsRolesInfo = auditTopicsRolesInfoService.findAuditTopicsRolesInfoById(id);
        if (auditTopicsRolesInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(auditTopicsRolesInfo);
    }

    @PostMapping("/audit-topics-roles-info")
    public AuditTopicsRolesInfo createAuditTopicsRolesInfo(@RequestBody AuditTopicsRolesInfo auditTopicsRolesInfo) {
        return auditTopicsRolesInfoService.saveOrUpdateAuditTopicsRolesInfo(auditTopicsRolesInfo);
    }

    @PutMapping("/audit-topics-roles-info/{id}")
    public ResponseEntity<AuditTopicsRolesInfo> updateAuditTopicsRolesInfo(@PathVariable(value = "id") Long id, @RequestBody AuditTopicsRolesInfo auditTopicsRolesInfo) {

        AuditTopicsRolesInfo updatedAuditTopicsRolesInfo = auditTopicsRolesInfoService.updateAuditTopicsRolesInfo(id, auditTopicsRolesInfo);
        if (updatedAuditTopicsRolesInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAuditTopicsRolesInfo);
    }
    @GetMapping("/getAllTopicWithoutParent")
    public List<AuditTopicRolesDTO>getAllTopicWithoutParent(){
        return auditTopicsRolesInfoService.getAllTopicWithoutParent();
    }
    @GetMapping("findByInfoName/{infoName}")
    public AuditTopicRolesDTO findByInfoName(@PathVariable String infoName){
        System.out.println(infoName);
        return auditTopicsRolesInfoService.findByInfoName(infoName);
    }


    // CRUD operations for AuditationInfo
        // Create operation
        @PostMapping("auditationInfo/createAuditationInfo")
        public ResponseEntity<AuditationInfo> create(@RequestBody AuditationInfo auditationInfo) {
            AuditationInfo createdAuditationInfo = auditationInfoService.createAuditationInfo(auditationInfo);
            return new ResponseEntity<>(createdAuditationInfo, HttpStatus.CREATED);
        }

        // Read operation
        @GetMapping("auditationInfo/getById/{id}")
        public ResponseEntity<AuditationInfo> getById(@PathVariable Long id) {
            AuditationInfo auditationInfo = auditationInfoService.getAuditationInfoById(id).get();
            return new ResponseEntity<>(auditationInfo, HttpStatus.OK);
        }
    // Get all operation
    @GetMapping("auditationInfo/getAll")
    public ResponseEntity<List<AuditationInfo>> getAll() {
        List<AuditationInfo> auditationInfo = auditationInfoService.getAllAuditationInfo();
        return new ResponseEntity<>(auditationInfo, HttpStatus.OK);
    }

        // Update operation
        @PutMapping("auditationInfo/updateAuditationInfo/{id}")
        public ResponseEntity<AuditationInfo> update(@PathVariable Long id, @RequestBody AuditationInfo auditationInfo) {
            AuditationInfo updatedAuditationInfo = auditationInfoService.updateAuditationInfo(id, auditationInfo);
            return new ResponseEntity<>(updatedAuditationInfo, HttpStatus.OK);
        }

        // Delete operation
        @DeleteMapping("auditationInfo/deleteAuditationInfo//{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {
            auditationInfoService.deleteAuditationInfoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        // Get all audit roles
        @GetMapping("/AuditRoles")
        public List<AuditRoles> getAllAuditRoles() {
            return auditRolesService.getAllAuditRoles();
        }

        // Get audit role by id
        @GetMapping("AuditRoles/getAuditRolesById/{id}")
        public ResponseEntity<AuditRoles> getAuditRoleById(@PathVariable(value = "id") Long auditRoleId)
                throws ResourceNotFoundException {
            AuditRoles auditRole = auditRolesService.getAuditRolesById(auditRoleId).get();
            return ResponseEntity.ok().body(auditRole);
        }

        // Create a new audit role
        @PostMapping("AuditRoles/createAuditRoles")
        public AuditRoles createAuditRole( @RequestBody AuditRoles auditRole) {
            return auditRolesService.saveAuditRoles(auditRole);
        }

        // Update an existing audit role
        @PutMapping("AuditRoles/updateAuditRoles/{id}")
        public ResponseEntity<AuditRoles> updateAuditRole(@PathVariable(value = "id") Long auditRoleId,
                                                           @RequestBody AuditRoles auditRoleDetails) throws ResourceNotFoundException {
            AuditRoles updatedAuditRole = auditRolesService.updateAuditRole(auditRoleId, auditRoleDetails);
            return ResponseEntity.ok(updatedAuditRole);
        }

        // Delete an audit role
        @DeleteMapping("AuditRoles/deleteAuditRoles/{id}")
        public ResponseEntity<?> deleteAuditRole(@PathVariable(value = "id") Long auditRoleId)
                throws ResourceNotFoundException {
            auditRolesService.deleteAuditRoles(auditRoleId);
            return ResponseEntity.ok().build();
        }
    }






