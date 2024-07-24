package com.empsys.ems.hr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empsys.ems.employee.Employee;
import com.empsys.ems.employee.EmployeeRepository;

@Controller
@RequestMapping("/hr")
public class HRController {
    private EmployeeRepository employeeRepository;

    public HRController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Add employee
    @GetMapping("/add_employee/{id}")
    String addEmployee(@PathVariable int id, Model model) {
        model.addAttribute("hr_id", id);
        model.addAttribute("employee", new Employee());
        return "hr_templates/add_employee_template";
    }

    @PostMapping("/add_employee/{id}")
    String submitEmployee(@ModelAttribute Employee employee, @PathVariable int id) {
        employeeRepository.save(employee);
        return "hr_templates/add_employee_template";
    }
}