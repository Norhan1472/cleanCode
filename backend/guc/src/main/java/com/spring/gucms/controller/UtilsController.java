package com.spring.gucms.controller;

import com.itextpdf.text.DocumentException;
import com.spring.gucms.dto.DataDetails;
import com.spring.gucms.dto.UtilsDTO;
import com.spring.gucms.entity.Utils;
import com.spring.gucms.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

        @GetMapping("/getAllUtils")
        public List<Utils> getAllUtils() {
            return utilsService.getAllUtils();
        }

        // Get audit role by id
        @GetMapping("Utils/getUtilsById/{id}")
        public ResponseEntity<Utils> getAuditRoleById(@PathVariable(value = "id") Long auditRoleId)
                throws ResourceNotFoundException {
            Utils utils = utilsService.getUtilsById(auditRoleId).get();
            return ResponseEntity.ok().body(utils);
        }

        // Create a new audit role
        @PostMapping("Utils/createUtils")
        public Utils createUtils( @RequestBody Map<String,Object> requestMap) {
            return utilsService.saveUtils(requestMap);
        }

        // Update an existing audit role
        //api/Utils/updateUtils/{id}
        @PutMapping("Utils/updateUtils/{id}")
        public ResponseEntity<Utils> updateUtil(@PathVariable(value = "id") Long auditRoleId,
                                                           @RequestBody Map<String,Object>requestMap) throws ResourceNotFoundException {
            Utils updatedUtil = utilsService.updateAuditRole(auditRoleId, requestMap);
            return ResponseEntity.ok(updatedUtil);//Utils updatedRole
        }

        // Delete an utils
        @DeleteMapping("Utils/deleteUtils/{id}")
        public ResponseEntity<?> deleteAuditRole(@PathVariable(value = "id") Long utilId)
                throws ResourceNotFoundException {
            utilsService.deleteUtils(utilId);
            return ResponseEntity.ok().build();
        }
        @GetMapping("utils/getUtilsDto")
        public ResponseEntity<List<UtilsDTO>>getUtilsDto(){
            System.out.println("helllpppp");
            List<UtilsDTO>dto = utilsService.getUtilsDto();
            return ResponseEntity.ok(dto);
        }
        @GetMapping("utils/getUtilById/{id}")
        public ResponseEntity<UtilsDTO>getUtilById(@PathVariable(value = "id") Long utilId){
            UtilsDTO dto =utilsService.getUtilsDtoById(utilId);
            return ResponseEntity.ok(dto);
        }
        @GetMapping("details/{id}")
        public ResponseEntity<List<DataDetails>>getByTopicId(@PathVariable(value = "id") Long topicId){

        return new ResponseEntity<>(utilsService.getByTopicId(topicId),HttpStatus.OK);
        }
        @GetMapping("utils/generateFunctionReport/{id}")
        public ResponseEntity<Object>generateFunctionReport(@PathVariable(value = "id") Long utilId) throws DocumentException, IOException {
            return utilsService.functionReport(utilId);

        }
    }






