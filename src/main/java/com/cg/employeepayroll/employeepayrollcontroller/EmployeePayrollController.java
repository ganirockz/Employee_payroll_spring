package com.cg.employeepayroll.employeepayrollcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employeepayroll.dto.EmployeePayrollDTO;
import com.cg.employeepayroll.dto.ResponseDTO;
import com.cg.employeepayroll.exceptions.UserNotFound;
import com.cg.employeepayroll.model.EmployeePayrollData;
import com.cg.employeepayroll.services.IEmployeePayrollService;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayrollController {
	
	@Autowired
	private IEmployeePayrollService employeePayrollService;
	
	@GetMapping("/get")
	public ResponseEntity<List<EmployeePayrollDTO>> getAllUser(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.getAllUser());
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<EmployeePayrollDTO> createUser(@Valid @RequestBody EmployeePayrollDTO user){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeePayrollService.createUser(user));
		}catch(UserNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<EmployeePayrollDTO> updateUser(@Valid @RequestBody EmployeePayrollDTO user){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.updateUser(user));
		}catch(UserNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EmployeePayrollDTO> deleteUser(@PathVariable("id")Long id){
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeePayrollService.deleteUser(id));
		}catch(UserNotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
