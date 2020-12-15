package com.cg.employeepayroll.dto;

import com.cg.employeepayroll.model.EmployeePayrollData;

import java.time.LocalDate;
import java.util.*;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EmployeePayrollDTO {
	
	private Long id;
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee name Invalid")
	private String name;
	@Pattern(regexp = "[0-9]+", message ="salary should be a number")
	private String basicPay;
	private LocalDate startDate;
	@Pattern(regexp="[MF]",message="gender should be single character")
	private Character gender;
	
	public EmployeePayrollDTO(EmployeePayrollData employeePayroll) {
		this.setId(employeePayroll.getId());
		this.setName(employeePayroll.getName());
		this.setBasicPay(employeePayroll.getBasicPay());
	}
	
	public EmployeePayrollDTO() {}
	
}
