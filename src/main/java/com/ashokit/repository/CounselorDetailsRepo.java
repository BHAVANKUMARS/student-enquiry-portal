package com.ashokit.repository;

import com.ashokit.entity.CounselorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CounselorDetailsRepo extends JpaRepository<CounselorDetails,Long> {

    CounselorDetails findByEmailAndPassword(String email,String password);

    CounselorDetails findByEmail(String email);


}
