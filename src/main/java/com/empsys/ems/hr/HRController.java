package com.empsys.ems.hr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empsys.ems.employee.Employee;


@Controller
@RequestMapping("/hr")
public class HRController {
    private HRRepository hrRepository;
    public HRController(HRRepository hrRepository) {
        this.hrRepository = hrRepository;
    }
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id, Model model) {
        HR emp=hrRepository.getHRById(id);
        model.addAttribute("emp", emp);

        return "hr_templates/dashboard";
    }

    @GetMapping("/add_employee")
    String addEmployee() {
        return "hr_templates/add_employee_template";
    }

    @PostMapping("/submit_employee")
    String submitEmployee(@RequestBody Employee employee) {
        return "hr_templates/add_employee_template";
    }

    @GetMapping("/logout")
    String logout() {
        
        return "homepage";
    }
}