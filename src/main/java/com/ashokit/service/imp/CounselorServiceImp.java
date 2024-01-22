package com.ashokit.service.imp;

import com.ashokit.constant.EnquiryStatus;
import com.ashokit.dto.DashboardResponse;
import com.ashokit.entity.CounselorDetails;
import com.ashokit.entity.EnquiryDetails;
import com.ashokit.exception.DataValidationException;
import com.ashokit.repository.CounselorDetailsRepo;
import com.ashokit.repository.EnquiryDetailsRepo;
import com.ashokit.service.CounselorService;
import com.ashokit.util.EmailUtil;
import com.ashokit.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CounselorServiceImp implements CounselorService {

    private static final Logger logger = LoggerFactory.getLogger(CounselorServiceImp.class);

    private static final String ENTERED_USERNAME_PASSWORD_NOT_FOUND = "Entered UserName/Password Not Found";

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private CounselorDetailsRepo counselorDetailsRepo;

    @Autowired
    private EnquiryDetailsRepo enquiryDetailsRepo;

    @Override
    public String saveCounselorDetail(CounselorDetails counselorDetailsRequest){

        logger.info("email "+counselorDetailsRequest.getEmail());
        //checking if email is already exist or not
        CounselorDetails counselorDetails =counselorDetailsRepo.findByEmail(counselorDetailsRequest.getEmail());

        logger.info("counselorDetails "+counselorDetails);

        if(counselorDetails == null) {

            counselorDetailsRepo.save(counselorDetailsRequest);

            return "Account Created";
//            return "Account Created, Check your Email";
        }
        else
            return "Choose Unique Email";

    }

    @Override
    public boolean checkCounselorCredential(CounselorDetails counselorDetailsRequest, HttpServletRequest servletRequest) {

        boolean isCounselorExist = false;

        logger.info("Login request "+counselorDetailsRequest.toString());

        CounselorDetails counselorDetails = counselorDetailsRepo.findByEmailAndPassword(counselorDetailsRequest.getEmail(), counselorDetailsRequest.getPassword());

        logger.info("db record "+counselorDetails);

        if(counselorDetails != null && counselorDetails.getId() != null) {

            // passing true value getSession(true) - create new session everytime when login
            HttpSession httpSession = servletRequest.getSession(true);

            httpSession.setAttribute("CID",counselorDetails.getId());

            isCounselorExist = true;


        }

        return isCounselorExist;

    }

    @Override
    public boolean recoverPassword(String email){

        boolean isEmailSent = false;

        logger.info("New password Sent Successfully");

        CounselorDetails counselorDetails = counselorDetailsRepo.findByEmail(email);

        if(counselorDetails != null)
            isEmailSent = emailUtil.sendEmail("bhavan01032000@gmail.com",email,"Recovered Email ",counselorDetails.getPassword());

        return isEmailSent;

    }

    @Override
    public DashboardResponse getTotalEnquires(Long counselorId){

        List<EnquiryDetails> enquiryDetailsList = enquiryDetailsRepo.findByCounselorId(counselorId);

        int totalEnquires = enquiryDetailsList.size();

        int enrolled = enquiryDetailsList.stream().filter(c->c.getEnquiryStatus().equals(EnquiryStatus.valueOf("ENROLLED"))).collect(Collectors.toList()).size();

        int lost = totalEnquires - enrolled;

        DashboardResponse dashboardResponse = new DashboardResponse(totalEnquires,enrolled,lost);

        logger.info("dashboardResponse "+dashboardResponse);

        return dashboardResponse;

    }



}
