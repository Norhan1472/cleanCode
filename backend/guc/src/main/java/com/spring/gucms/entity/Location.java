package com.spring.gucms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Auditation_Info")
public class Location extends BaseEntityCreateAndUpdate  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Audit_ID")
    private Long auditId;

    @Column(name = "File_Name")
    private String fileName;

    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "File_Type_ID")
    @Column(name = "File_Type_ID")
    private String fileType;

    @Column(name = "Start_Date")
    private Timestamp startDate;

    @Column(name = "End_Date")
    private Timestamp endDate;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "Folder_ID")
    @Column(name = "Folder_ID")
    private String folder_id;


}