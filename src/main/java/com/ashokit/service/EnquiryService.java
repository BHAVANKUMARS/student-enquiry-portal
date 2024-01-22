package com.ashokit.service;

import com.ashokit.dto.SearchCriteria;
import com.ashokit.entity.EnquiryDetails;
import java.util.List;

public interface EnquiryService {

    boolean saveStudentEnquiryDetails(EnquiryDetails enquiryDetailsRequest);

    List<EnquiryDetails> getStudentEnquiryDetails(Long counselorId, SearchCriteria searchCriteria);


}
