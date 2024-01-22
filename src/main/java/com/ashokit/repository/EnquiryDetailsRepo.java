package com.ashokit.repository;

import com.ashokit.entity.EnquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnquiryDetailsRepo extends JpaRepository<EnquiryDetails,Long> {

    List<EnquiryDetails> findByCounselorId(Long counselorId);

}
