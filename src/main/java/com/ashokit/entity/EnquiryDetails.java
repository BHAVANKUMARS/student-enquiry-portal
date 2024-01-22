package com.ashokit.entity;

import com.ashokit.constant.ClassMode;
import com.ashokit.constant.Courses;
import com.ashokit.constant.EnquiryStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "enquiry_details")
@Data
@NoArgsConstructor
@ToString
public class EnquiryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "counselor_id")
    private Long counselorId;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private ClassMode classMode;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Courses course;

    @Enumerated(EnumType.STRING)
    private EnquiryStatus enquiryStatus;

    @PrePersist
    public void assignCurrentDate(){

        this.createdAt = new Date();

        this.updatedAt = new Date();

    }

    @PreUpdate
    public void updateCurrentDate(){

        this.updatedAt = new Date();

    }

    public EnquiryDetails(EnquiryDetails enquiryDetails) {
        this.studentName = enquiryDetails.studentName;
        this.createdAt = enquiryDetails.createdAt;
        this.counselorId = enquiryDetails.counselorId;
        this.contactNumber = enquiryDetails.contactNumber;
        this.classMode = enquiryDetails.classMode;
        this.updatedAt = enquiryDetails.updatedAt;
        this.course = enquiryDetails.course;
        this.enquiryStatus = enquiryDetails.enquiryStatus;
    }
}
