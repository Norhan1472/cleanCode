package com.spring.gucms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataDetails {
    private long topicId;
    private String topicName;
    private String topicDescription;
}
