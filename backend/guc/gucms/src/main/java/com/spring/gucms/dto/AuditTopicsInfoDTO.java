package com.spring.gucms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.gucms.entity.AuditTopicsRolesInfo;
import com.spring.gucms.entity.AuditationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AuditTopicsInfoDTO {
    private Long topic_id;
    private String problem_name;
    private AuditationInfo audit;

    private Set<String> info_ids =new HashSet<>();

    private String investigate_Notes;
    private String investigate_recommendation;

    private Date created_date;
    private int created_user_id;
    private Date last_updated_date;
    private int last_updated_user_id;

}
