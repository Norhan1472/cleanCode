package com.spring.gucms.controller;

import com.spring.gucms.dto.IssueInfoDTO;
import com.spring.gucms.entity.IssueInfo;
import com.spring.gucms.entity.Utils;
import com.spring.gucms.repository.IssueInfoRepository;
import com.spring.gucms.repository.UtilsRepository;
import com.spring.gucms.service.IssueInfoService;
import com.spring.gucms.service.UtilsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Boolean.TRUE;

@RestController
@RequestMapping("/api/v1/issueInfo")
@CrossOrigin("*")
//@AllArgsConstructor
public class IssueInfoController {
    private IssueInfoService issueInfoService;
    private IssueInfoRepository issueInfoRepository;
    private UtilsService utilsService;
    @Autowired
    public IssueInfoController(IssueInfoService issueInfoService, IssueInfoRepository issueInfoRepository, UtilsService utilsService) {
        this.issueInfoService = issueInfoService;
        this.issueInfoRepository = issueInfoRepository;
        this.utilsService = utilsService;
    }

    //http://localhost:8080/api/v1/issueInfo/getAllIssueInfo
    @GetMapping("getAllIssueInfo")
    public List<IssueInfo> getAllIssueInfo()
    {
        return issueInfoService.getAllIssueInfo();

    }
    //http://localhost:8080/api/v1/IssueInfo/getIssueInfoById/1
    @GetMapping("getIssueInfoById/{id}")
    public IssueInfo getIssueInfoById(@PathVariable long id){
        return issueInfoService.getIssueInfoById(id);
    }
    //api/v1/issueInfo/addIssueInfo
    @PostMapping("addIssueInfo")
    public IssueInfo addIssueInfo(@RequestBody IssueInfo issueInfo){

        return issueInfoService.addIssueInfo(issueInfo);
    }
    @PostMapping("updateIssueInfo")
    public IssueInfo updateIssueInfo(@RequestBody IssueInfo issueInfo,int id){
        return issueInfoService.updateIssueInfo(issueInfo,id);
    }
    @DeleteMapping("deleteIssueInfo/{id}")
    public Boolean deleteIssueInfo(@PathVariable long id){
        List<Utils>util = utilsService.getTopicsById(id);
        for (int i=0;i<util.size();i++){
            utilsService.deleteUtils(util.get(i).getRole_ID());
        }
        issueInfoRepository.deleteById(id);
        return TRUE;
    }
    @GetMapping("getAll")
    public List<IssueInfoDTO>getAll(){
        return issueInfoService.getAllIssueInfoDto();
    }

}
