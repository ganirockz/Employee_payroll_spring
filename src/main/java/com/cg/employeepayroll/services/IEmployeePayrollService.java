package com.cg.employeepayroll.services;

import java.util.List;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.model.EmployeePayrollData;

public interface IEmployeePayrollService {
	List<EmployeePayrollData> getEmployeeData();
	EmployeePayrollData getEmployeePayrollById(int empId);
	
	EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO);
	
	EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO);
	
	void deleteEmployeePayrollData(int empId);
}
