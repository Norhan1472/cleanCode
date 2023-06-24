package com.spring.gucms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntityCreateAndUpdate {


    @Column(name="created_date")
    @Temporal(TemporalType.DATE)
    private Date created_date;
    @Column(name="created_user_id")
    private int created_user_id;
    @Column(name="last_updated_date")
    @Temporal(TemporalType.DATE)
    private Date last_updated_date;
    @Column(name="last_updated_user_id")
    private int last_updated_user_id;



    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getCreated_user_id() {
        return created_user_id;
    }

    public void setCreated_user_id(int created_user_id) {
        this.created_user_id = created_user_id;
    }

    public Date getLast_updated_date() {
        return last_updated_date;
    }

    public void setLast_updated_date(Date last_updated_date) {
        this.last_updated_date = last_updated_date;
    }

    public int getLast_updated_user_id() {
        return last_updated_user_id;
    }

    public void setLast_updated_user_id(int last_updated_user_id) {
        this.last_updated_user_id = last_updated_user_id;
    }

}
