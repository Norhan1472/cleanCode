package com.spring.gucms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Audit_Roles")
public class Utils extends BaseEntityCreateAndUpdate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_ID")
    private Long role_ID;
    @ManyToOne
    @JoinColumn(name = "Topic_ID")
    private IssueInfo auditTopic;
    @ManyToOne
    @JoinColumn(name = "Info_ID")
    private Topics auditTopicRoleInfo;
    @Column(name = "investigate_notes")
    private String investigate_notes;
    @Column(name = "investigate_recommendation")
    private String investigate_recommendation;
}
