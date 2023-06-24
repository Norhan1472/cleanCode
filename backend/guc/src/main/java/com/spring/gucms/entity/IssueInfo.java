package com.spring.gucms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Audit_Topics_info")
public class IssueInfo extends BaseEntityCreateAndUpdate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topic_id")
    private Long topic_id;
    @Column(name="problem_name")
    private String problem_name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audit_ID")
    private Location audit;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="topic_info",
           joinColumns = @JoinColumn(
                    name = "topic_id"
            ),
            inverseJoinColumns = @JoinColumn(
                   name = "info_id"
            )
    )
    //@JsonIgnore
    private Set<Topics> topics_ids =new HashSet<>();
    @Column(name="investigate_Notes")
    private String investigate_Notes;
    @Column(name="investigate_recommendation")
    private String investigate_recommendation;

}
