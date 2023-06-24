package com.spring.gucms.service;

import com.spring.gucms.entity.AuditRoles;
import com.spring.gucms.repository.AuditRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuditRolesService {

    private final AuditRolesRepository auditRolesRepository;

    @Autowired
    public AuditRolesService(AuditRolesRepository auditRolesRepository) {
        this.auditRolesRepository = auditRolesRepository;
    }

    // Define methods for working with the AuditRoles entity

    public AuditRoles updateAuditRole(Long roleId, AuditRoles updatedRole) {
        Optional<AuditRoles> existingRole = auditRolesRepository.findById(roleId);

        if (existingRole.isPresent()) {
            AuditRoles roleToUpdate = existingRole.get();
            roleToUpdate.setInvestigate_notes(updatedRole.getInvestigate_notes());
            roleToUpdate.setInvestigate_recommendation(updatedRole.getInvestigate_recommendation());

            roleToUpdate.setAuditTopic(updatedRole.getAuditTopic());
            roleToUpdate.setAuditTopicRoleInfo(updatedRole.getAuditTopicRoleInfo());

            roleToUpdate.setCreated_date(updatedRole.getCreated_date());
            roleToUpdate.setCreated_user_id(updatedRole.getCreated_user_id());
            roleToUpdate.setLast_updated_date(new Date());
            roleToUpdate.setLast_updated_user_id(updatedRole.getLast_updated_user_id());
            return auditRolesRepository.save(roleToUpdate);
        } else {
            throw new ResourceNotFoundException("Audit Role not found with id: " + roleId);
        }
    }


    public List<AuditRoles> getAllAuditRoles() {
        return auditRolesRepository.findAll();
    }

    public Optional<AuditRoles> getAuditRolesById(Long id) {
        return auditRolesRepository.findById(id);
    }

    public AuditRoles saveAuditRoles(AuditRoles auditRoles) {
        return auditRolesRepository.save(auditRoles);
    }

    public void deleteAuditRoles(Long id) {
        auditRolesRepository.deleteById(id);
    }


}


