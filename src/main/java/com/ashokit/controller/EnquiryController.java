package com.ashokit.controller;

import com.ashokit.dto.DashboardResponse;
import com.ashokit.dto.SearchCriteria;
import com.ashokit.entity.EnquiryDetails;
import com.ashokit.service.EnquiryService;
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
import java.util.List;

@Controller
public class EnquiryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnquiryController.class);

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/addStudentEnquiry")
    public String addStudentEnquiry(Model model,HttpServletRequest req){

        HttpSession session = req.getSession(false);
        Long cid = (Long) session.getAttribute("CID");

        if (cid == null) {
            return "redirect:/logout";
        }

        model.addAttribute("enquiryDetail",new EnquiryDetails());

        return "addEnquires";

    }

    @PostMapping("/addStudentEnquiry")
    public String saveStudentEnquires(@ModelAttribute ("enquiryDetail") EnquiryDetails enquiryDetails, Model model, HttpServletRequest request){

        LOGGER.info("enquiryDetails "+enquiryDetails);

        //false - get the existing session
        HttpSession httpSession = request.getSession(false);

        Long counselorId = null;

        if(httpSession.getAttribute("CID") != null) {

            counselorId = (Long) httpSession.getAttribute("CID");

            enquiryDetails.setCounselorId(counselorId);

        }else
            return "redirect:/index";

        boolean isStudentEnquiresSaved = enquiryService.saveStudentEnquiryDetails(enquiryDetails);

        if(isStudentEnquiresSaved) {

            model.addAttribute("msg","Enquiry Added");

            return "addEnquires";

        }else {

            model.addAttribute("errorMsg","Enquiry Not Added");

            return "addEnquires";
        }

    }

    @GetMapping("/viewStudentEnquiry")
    public String viewStudentEnquiry(Model model,HttpServletRequest servletRequest){

        HttpSession httpSession = servletRequest.getSession(false);

        Long counselorId = (Long) httpSession.getAttribute("CID");

        if (counselorId == null) {
            return "redirect:/logout";
        }

        List<EnquiryDetails> enquiryDetailsList = enquiryService.getStudentEnquiryDetails(counselorId,new SearchCriteria());

        model.addAttribute("enquiries",enquiryDetailsList);

        model.addAttribute("sc",new SearchCriteria());

        return "viewEnquires";

    }

    @PostMapping("/filter-enquiries")
    public String filterEnquiry(@ModelAttribute ("sc") SearchCriteria searchCriteria,Model model,HttpServletRequest servletRequest){

        HttpSession httpSession = servletRequest.getSession(false);

        Long counselorId = null;

        if(httpSession.getAttribute("CID") != null)
            counselorId = (Long) httpSession.getAttribute("CID");

        if(counselorId == null)
            return "redirect:/index";

        List<EnquiryDetails> enquiryDetailsList = enquiryService.getStudentEnquiryDetails(counselorId,searchCriteria);

        model.addAttribute("enquiries",enquiryDetailsList);

        return "filterEnquiries";


    }

}
