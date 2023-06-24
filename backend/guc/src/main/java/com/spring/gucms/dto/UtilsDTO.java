package com.spring.gucms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UtilsDTO {
    private long role_ID;
    private String auditTopic;
    private String auditTopicRoleInfo;
    private String investigate_notes;
    private String investigate_recommendation;
}
