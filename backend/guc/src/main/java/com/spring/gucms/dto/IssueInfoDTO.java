package com.spring.gucms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IssueInfoDTO {
    private Long topic_id;
    private String problem_name;
    private String investigate_recommendation;
    private String investigate_Notes;
    private Set<String> infoName = new HashSet<>();
    private LocalDateTime created_date;
    private LocalDateTime last_updated_date;

}
