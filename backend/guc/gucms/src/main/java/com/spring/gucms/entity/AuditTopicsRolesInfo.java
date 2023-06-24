package com.spring.gucms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "audit_topics_roles_info")
public class AuditTopicsRolesInfo extends BaseEntityCreateAndUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id" )//, insertable = false, updatable = false
    private Long info_ID;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "info_id")
    private AuditTopicsRolesInfo parent;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)//Avoiding empty json arrays.objects
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<AuditTopicsRolesInfo> children = new HashSet<>();

    @Column(name = "info_name")
    private String infoName;

    @Column(name = "info_desc")
    private String Info_Desc;

    @ManyToMany(mappedBy = "info_ids")
    @JsonIgnore
    private Set<AuditTopicsInfo>auditTopicsInfos=new HashSet<>();

}