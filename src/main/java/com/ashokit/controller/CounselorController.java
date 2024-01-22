package com.ashokit.controller;


import com.ashokit.dto.DashboardResponse;
import com.ashokit.entity.CounselorDetails;
import com.ashokit.service.CounselorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class CounselorController {

    private static final String RECORD_CREATED_SUCCESSFULLY = "Record Created Successfully";

    private static final String LOGIN_SUCCESSFULLY = "Login Successfully";

    private static final String MAIL_SENT_SUCCESSFULLY = "Mail Sent Successfully";

    private static final Logger LOGGER = LoggerFactory.getLogger(CounselorController.class);

    @Autowired
    private CounselorService counselorService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession httpSession = request.getSession(false);

        //delete the session
        httpSession.invalidate();

        return "redirect:/";

    }

    @GetMapping(value = "/")
    public String index(Model model){

        model.addAttribute("loginDetails",new CounselorDetails());

        return "index";
    }

    @PostMapping(value = "/login")
    public String validateCounselor(@ModelAttribute ("loginDetails") CounselorDetails counselorDetails, Model model, HttpServletRequest servletRequest){

        LOGGER.info("counselorDetails "+counselorDetails);

        boolean isCounselorExist = counselorService.checkCounselorCredential(counselorDetails,servletRequest);

        if(isCounselorExist)
            return "redirect:/dashboard";
        else {

            model.addAttribute("errMsg","Invalid Credential");

            return "index";
        }

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model,HttpServletRequest request){

        //false - get the existing session
        HttpSession httpSession = request.getSession(false);

        Long counselorId = null;

        if(httpSession.getAttribute("CID") != null) {

            counselorId = (Long) httpSession.getAttribute("CID");

        }else
            return "redirect:/index";


        DashboardResponse dashboardResponse = counselorService.getTotalEnquires(counselorId);

        model.addAttribute("enquires",dashboardResponse);

        return "dashboard";

    }

    @GetMapping(value = "/register")
    public String registration(Model model){

        model.addAttribute("counselor",new CounselorDetails());

        return "registrationView";

    }

    @PostMapping(value = "/register")
    public String saveCounselorDetails(@ModelAttribute ("counselor") CounselorDetails counselor,Model model){

        LOGGER.info("Counselor Details "+counselor.toString());

        String message = counselorService.saveCounselorDetail(counselor);

        LOGGER.info("message "+message);

        if(message.equals("Choose Unique Email"))
            model.addAttribute("errorMsg",message);
        else
            model.addAttribute("msg",message);

        return "registrationView";

    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }


    @PostMapping("/forgotPassword")
    public String sendEmailTo(@RequestParam(name = "email") String email,Model model){

        boolean isEmailSent = counselorService.recoverPassword(email);

        if(isEmailSent){

            model.addAttribute("msg","Password Sent to Email");

            return "forgotPassword";

        }else {

            model.addAttribute("errorMsg","Invalid Email");

            return "forgotPassword";
        }


    }
}
