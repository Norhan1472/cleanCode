package com.spring.gucms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TopicsDTO {
    private Long info_ID;
    private long parent;

    private String info_Name;

    private String Info_Desc;
}
