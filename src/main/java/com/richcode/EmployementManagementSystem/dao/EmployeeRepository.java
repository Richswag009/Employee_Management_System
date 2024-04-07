package com.richcode.EmployementManagementSystem.dao;

import com.richcode.EmployementManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee ,Integer> {
    public List<Employee> findAllByOrderByLastNameAsc();

    boolean existsEmployeeByEmail(String email);

}
