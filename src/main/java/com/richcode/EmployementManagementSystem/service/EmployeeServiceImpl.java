package com.richcode.EmployementManagementSystem.service;


import com.richcode.EmployementManagementSystem.dao.EmployeeRepository;
import com.richcode.EmployementManagementSystem.entity.Employee;
import com.richcode.EmployementManagementSystem.exception.DuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(Integer theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        if(employeeRepository.existsEmployeeByEmail(theEmployee.getEmail())){
            throw new DuplicateException(
                    "email already taken"
            );
        }
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(Integer theId) {
        employeeRepository.deleteById(theId);
    }

}






