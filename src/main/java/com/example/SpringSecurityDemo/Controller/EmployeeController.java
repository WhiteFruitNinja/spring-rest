package com.example.SpringSecurityDemo.Controller;

import com.example.SpringSecurityDemo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.SpringSecurityDemo.Entity.Employee;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //GetAll Rest Api
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    //Saves Employees
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //Get by Id Rest Api
    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    // localhost:8080/api/employees/1
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeID){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeID),HttpStatus.OK);
    }

    //Update Rest Api
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }

    //Delete Rest Api
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        //delete employee from db
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted Successfully.",HttpStatus.OK);
    }

}
