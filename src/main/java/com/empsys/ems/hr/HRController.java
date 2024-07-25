package com.empsys.ems.hr;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empsys.ems.employee.Employee;
import com.empsys.ems.employee.EmployeeRepository;

@Controller
@RequestMapping("/hr")
public class HRController {
    private EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public HRController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    // Add employee
    @GetMapping("/add_employee")
    String addEmployee(Model model) {
        int id = Integer.parseInt(getAuth().getName());
        model.addAttribute("hr_id", id);
        model.addAttribute("employee", new Employee());
        return "hr_templates/add_employee_template";
    }

    @PostMapping("/add_employee")
    String submitEmployee(@ModelAttribute Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "hr_templates/add_employee_template";
    }

    Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}