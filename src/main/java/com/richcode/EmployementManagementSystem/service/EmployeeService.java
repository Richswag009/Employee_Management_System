package com.richcode.EmployementManagementSystem.service;


import com.richcode.EmployementManagementSystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer theId);

    Employee save(Employee theEmployee);

    void deleteById(Integer theId);

}
