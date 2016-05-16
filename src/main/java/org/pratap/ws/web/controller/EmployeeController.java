package org.pratap.ws.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.pratap.ws.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private static Map<String, Employee> employeeList;
	
	private static Employee saveEmployee(Employee emp){
		
		if(null==employeeList){
			employeeList = new HashMap<String, Employee>();
		}
		employeeList.put(emp.getEmpId(), emp);
		return emp;
	}
	
	static{
		for(int i=0; i<10 + (int)(Math.random() * 30);i++){
			saveEmployee(new Employee(RandomStringUtils.random( 5, true, false), RandomStringUtils.random( 10, true, false), 25 + (int)(Math.random() * 70)));
		}	
	}

	@RequestMapping(value="/api/employees-list",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployeesList(){
		Collection<Employee> employees = employeeList.values();
		return new ResponseEntity<Collection<Employee>>(employees,HttpStatus.OK);
	}
	
	
}
