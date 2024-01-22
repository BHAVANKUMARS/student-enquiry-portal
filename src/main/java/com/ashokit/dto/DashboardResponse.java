package com.ashokit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DashboardResponse {

    private int totalEnquires;

    private int enrolled;

    private int lost;

}
