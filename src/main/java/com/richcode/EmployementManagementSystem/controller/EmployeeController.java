package com.richcode.EmployementManagementSystem.controller;

import com.richcode.EmployementManagementSystem.dao.EmployeeRepository;
import com.richcode.EmployementManagementSystem.entity.Employee;
import com.richcode.EmployementManagementSystem.exception.DuplicateException;
import com.richcode.EmployementManagementSystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    add initBinder to remover trim input strings
    //    remove leading and trailing whitespace
    @InitBinder
    public void initBuilder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,trimmerEditor);
    }

    @GetMapping("/employees")
    public  String getEmployees(Model model){
        List<Employee> employees= employeeService.findAll();
        Employee oneEmployee = employeeService.findById(14);
        model.addAttribute("employees",employees);
        model.addAttribute("oneEmployee",oneEmployee);
        return "employees";

    }

    @GetMapping("/addEmployee-form")
    public String showForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "addEmployee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute
            Employee employee,Model model
            ) throws DuplicateException {
        boolean emailExist = employeeRepository.existsEmployeeByEmail(employee.getEmail());
        if(!emailExist){
            employeeRepository.save(employee);

        }else{
            model.addAttribute("errorMessage", "Email already taken");
            return "addEmployee-form";
        }

//        employeeService.save(employee);
        return "redirect:/api/v1/employees";
    }
    @PutMapping("/update")
    public String updateEmployee(
            @ModelAttribute("employee")
            Employee employee
            ){
        employeeService.save(employee);
        return "redirect:/api/v1/employees";
    }


    @GetMapping("/addEmployee-form/{employeeId}")
    public String editEmployeeById(
            @PathVariable (value="employeeId")
            Model theModel, Integer theId){
        Employee employee= employeeService.findById(theId);
        System.out.println(employee);
        theModel.addAttribute("employee",employee);
//        return "edit-employee";
        return "addEmployee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(
            @RequestParam("employeeId")
            @ModelAttribute("employee")
            int theId
    ){
        employeeService.deleteById(theId);
        return "redirect:/api/v1/employees";
    }
}
