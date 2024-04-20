package com.empsys.ems.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // login
    @GetMapping("/login")
    String employeeLogin() {
        return "employee_templates/loginpage";
    }

    @PostMapping("/login")
    String employeeLoginSubmission(@RequestParam int id, @RequestParam String password) {
        Employee emp = employeeRepository.getEmployeeById(id);
        if (emp.getDesignation().equals("Employee")) {
            return "redirect:/employee/"+emp.getId();
        }
        return "employee_templates/loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id, Model model) {
        Employee emp = employeeRepository.getEmployeeById(id);
        model.addAttribute("emp", emp);
        return "employee_templates/dashboard";
    }

    @GetMapping("/logout")
    String logout() {

        return "redirect:/";
    }
}
