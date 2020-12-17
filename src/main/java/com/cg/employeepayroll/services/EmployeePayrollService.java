package com.cg.employeepayroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.exceptions.DetailsNotProvidedExceptions;
import com.cg.employeepayroll.exceptions.EmployeePayrollException;
import com.cg.employeepayroll.exceptions.UserNotFound;
import com.cg.employeepayroll.model.EmployeePayrollData;
import com.cg.employeepayroll.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{	
	@Autowired
    private EmployeePayrollRepository employeePayrollRepository;
	
	@Override
	public List<EmployeePayrollDTO> getAllUser(){
		return employeePayrollRepository.findAll().stream()
				.map(employeePayroll -> new EmployeePayrollDTO(employeePayroll))
				.collect(Collectors.toList());
    }
	
	@Override
	public EmployeePayrollDTO createUser(EmployeePayrollDTO employeePayrollDTO) {
		if(Objects.nonNull(employeePayrollDTO.getName()) && Objects.nonNull(employeePayrollDTO.getSalary())){
			EmployeePayrollData employeePayroll = new EmployeePayrollData(employeePayrollDTO.getName(), employeePayrollDTO.getSalary(),employeePayrollDTO.getGender(),employeePayrollDTO.getStartDate());
			return new EmployeePayrollDTO(employeePayrollRepository.save(employeePayroll));
		}
		throw new EmployeePayrollException("Id is incorrect");
	}
	
	@Override
	public EmployeePayrollDTO updateUser(EmployeePayrollDTO employeePayrollDTO) {
		return employeePayrollRepository.findById(employeePayrollDTO.getId()).map(employeePayroll -> {
			if(Objects.nonNull(employeePayrollDTO.getName())) {
				employeePayroll.setName(employeePayrollDTO.getName());
			}
			if(Objects.nonNull(employeePayrollDTO.getSalary())) {
				employeePayroll.setSalary(employeePayrollDTO.getSalary());
			}
			return new EmployeePayrollDTO(employeePayrollRepository.save(employeePayroll));
		}).orElseThrow(() -> new UserNotFound("UserNotFound"));
	}
	
	@Override
	public EmployeePayrollDTO deleteUser(Long id) {
		return employeePayrollRepository.findById(id).map(employeePayroll -> {
			employeePayrollRepository.deleteById(employeePayroll.getId());
			return new EmployeePayrollDTO(employeePayroll);
		}).orElseThrow(() -> new UserNotFound("UserNOtFound"));
	}
}
