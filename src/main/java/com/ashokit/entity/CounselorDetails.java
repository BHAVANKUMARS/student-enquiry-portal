package com.ashokit.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "counselor_details")
@Data
@NoArgsConstructor
@ToString
public class CounselorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void assignCurrentDate(){

        this.createdAt = new Date();

        this.updatedAt = new Date();

    }

    @PreUpdate
    public void updateCurrentDate(){

        this.updatedAt = new Date();

    }

    public CounselorDetails(CounselorDetails counselorDetail) {
        this.name = counselorDetail.name;
        this.createdAt = counselorDetail.createdAt;
        this.email = counselorDetail.email;
        this.password = counselorDetail.password;
        this.phoneNumber = counselorDetail.phoneNumber;
        this.updatedAt = counselorDetail.updatedAt;
    }
}
