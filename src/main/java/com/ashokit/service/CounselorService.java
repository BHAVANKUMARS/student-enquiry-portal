package com.ashokit.service;

import com.ashokit.dto.DashboardResponse;
import com.ashokit.entity.CounselorDetails;

import javax.servlet.http.HttpServletRequest;

public interface CounselorService {

    String saveCounselorDetail(CounselorDetails counselorDetailsRequest);

    boolean checkCounselorCredential(CounselorDetails counselorDetailsRequest, HttpServletRequest servletRequest);

    boolean recoverPassword(String email);

    DashboardResponse getTotalEnquires(Long counselorId);



}
