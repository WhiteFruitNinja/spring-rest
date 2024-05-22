package com.example.SpringSecurityDemo.Service;

import com.example.SpringSecurityDemo.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    //save employee in database
    Employee saveEmployee(Employee employee);

    //get all employee from database
    List<Employee> getAllEmployee();

    //get employee using id
    Employee getEmployeeById(long id);

    //update employee
    Employee updateEmployee(Employee employee, long id);

    //delete employee
    void deleteEmployee(long id);
}
