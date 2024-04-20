package com.empsys.ems.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }
    @GetMapping("/{id}")
    String dashboard(@PathVariable int id, Model model) {
        Employee emp=employeeRepository.getEmployeeById(id);
        model.addAttribute("emp", emp);

        return "employee_templates/dashboard";
    }

    @GetMapping("/logout")
    String logout() {
        
        return "homepage";
    }
}
