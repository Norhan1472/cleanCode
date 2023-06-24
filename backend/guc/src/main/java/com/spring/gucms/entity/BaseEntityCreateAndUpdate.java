package com.spring.gucms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Setter
@Getter
public class BaseEntityCreateAndUpdate {
    @Column(name="created_date")
    private LocalDateTime created_date;
    @Column(name="created_user_id")
    private int created_user_id;
    @Column(name="last_updated_date")
    private LocalDateTime last_updated_date;
    @Column(name="last_updated_user_id")
    private int last_updated_user_id;

    @PrePersist
    protected void onCreate()
    {
        created_date= LocalDateTime.now();
        last_updated_date=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate()
    {
        last_updated_date=LocalDateTime.now();
    }




}
