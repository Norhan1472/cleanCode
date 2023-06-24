package com.spring.gucms.controller;

import com.spring.gucms.dto.AuditTopicsInfoDTO;
import com.spring.gucms.entity.AuditTopicsInfo;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.service.AuditTopicsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auditTopicsInfo")
@CrossOrigin("http://localhost:4200")
public class AuditTopicsInfoController {
    private AuditTopicsInfoService auditTopicsInfoService;

    @Autowired
    public AuditTopicsInfoController(AuditTopicsInfoService auditTopicsInfoService) {
        this.auditTopicsInfoService = auditTopicsInfoService;
    }
    @GetMapping("getAllAuditTopicsInfo")
    public List<AuditTopicsInfo> getAllAuditTopicsInfo(){

        return auditTopicsInfoService.getAllAuditTopicsInfo();
    }
    @GetMapping("getAuditTopicsInfoById/{id}")
    public AuditTopicsInfoDTO getAuditTopicsInfoById(@PathVariable long id){
         AuditTopicsInfo audits= new AuditTopicsInfo();
        audits=auditTopicsInfoService.getAuditTopicsInfoById(id);


        return auditTopicsInfoService.getAuditTopicsInfoByIdInDTO(id);
    }
    //api/v1/auditTopicsInfo/addAuditTopicsInfo
    @PostMapping("addAuditTopicsInfo")
    public void addAuditTopicsInfo(@RequestBody AuditTopicsInfo auditTopicsInfo){

         auditTopicsInfoService.addAuditTopicsInfo(auditTopicsInfo);
    }
    @PostMapping("updateAuditTopicsInfo")
    public AuditTopicsInfo updateAuditTopicsInfo(@RequestBody AuditTopicsInfo auditTopicsInfo,long id){
        return auditTopicsInfoService.updateAuditTopicsInfo(auditTopicsInfo,id);
    }
    @DeleteMapping("deleteAuditTopicsInfo/{id}")
    public Boolean deleteAuditTopicsInfo(@PathVariable long id){
        return auditTopicsInfoService.deleteAuditTopicsInfo(id);
    }

}
