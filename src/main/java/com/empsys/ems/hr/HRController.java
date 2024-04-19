package com.empsys.ems.hr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empsys.ems.employee.Employee;
import com.empsys.ems.manager.Manager;



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

    @PostMapping("/add_employee")
    String submitEmployee(@RequestParam int id, @RequestParam String name, @RequestParam String designation, @RequestParam int salary, @RequestParam float exp) {
        //hr added an employee
        Employee emp;
        if (designation.equals("Employee")) {
            emp = new Employee(id, name, designation, salary, exp);
        } else if (designation.equals("HR")) {
            emp = new HR(id, name, designation, salary, exp);
        } else if (designation.equals("manager")) {
            emp = new Manager(id, name, designation, salary, exp);
        }
        
        return "hr_templates/add_employee_template";
    }

    @GetMapping("/add_employee")
    String addEmployee() {
        return "hr_templates/add_employee_template";
    }

    

    @GetMapping("/logout")
    String logout() {
        
        return "homepage";
    }
}