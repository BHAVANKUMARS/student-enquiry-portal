package com.ashokit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SearchCriteria {

    private String course;

    private String classMode;

    private String enquiryStatus;

}
