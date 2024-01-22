package com.ashokit.service.imp;

import com.ashokit.constant.ClassMode;
import com.ashokit.constant.Courses;
import com.ashokit.constant.EnquiryStatus;
import com.ashokit.controller.EnquiryController;
import com.ashokit.dto.SearchCriteria;
import com.ashokit.entity.EnquiryDetails;
import com.ashokit.repository.EnquiryDetailsRepo;
import com.ashokit.service.EnquiryService;
import com.ashokit.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EnquiryServiceImp implements EnquiryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryServiceImp.class);


    @Autowired
    private EnquiryDetailsRepo enquiryDetailsRepo;

    @Override
    public boolean saveStudentEnquiryDetails(EnquiryDetails enquiryDetailsRequest) {

    boolean isStudentEnquiresSaved = false;

        EnquiryDetails enquiryDetails = enquiryDetailsRepo.save(enquiryDetailsRequest);

        if(enquiryDetails.getId() != null)
            return true;

        return isStudentEnquiresSaved;

    }

    @Override
    public List<EnquiryDetails> getStudentEnquiryDetails(Long counselorId, SearchCriteria searchCriteria) {

        LOGGER.info("searchCriteria "+searchCriteria);

        EnquiryDetails enquiryDetails = new EnquiryDetails();

        enquiryDetails.setCounselorId(counselorId);

        if(StringUtil.isNotNullAndNotEmpty(searchCriteria.getClassMode()))
            enquiryDetails.setClassMode(ClassMode.valueOf(searchCriteria.getClassMode()));

        if(StringUtil.isNotNullAndNotEmpty(searchCriteria.getEnquiryStatus()))
            enquiryDetails.setEnquiryStatus(EnquiryStatus.valueOf(searchCriteria.getEnquiryStatus()));

        if(StringUtil.isNotNullAndNotEmpty(searchCriteria.getCourse()))
            enquiryDetails.setCourse(Courses.valueOf(searchCriteria.getCourse()));

        Example<EnquiryDetails> enquiryDetailsExample = Example.of(enquiryDetails);

        List<EnquiryDetails> enquiryDetailsList =  enquiryDetailsRepo.findAll(enquiryDetailsExample);

        return enquiryDetailsList;

    }




}
