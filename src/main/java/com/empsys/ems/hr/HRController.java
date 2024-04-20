package com.empsys.ems.hr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empsys.ems.employee.Employee;

@Controller
@RequestMapping("/hr")
public class HRController {
    private HRRepository hrRepository;

    public HRController(HRRepository hrRepository) {
        this.hrRepository = hrRepository;
    }

    @GetMapping("/login")
    String hRLogin() {
        return "hr_templates/loginpage";
    }

    @PostMapping("/login")
    String hRLoginSubmission(@RequestParam int id, @RequestParam String password) {
        Employee emp = hrRepository.getEmployeeById(id);
        if (emp.getDesignation().equals("HR")) {
            return "redirect:/hr/" + emp.getId();
        }
        return "hr_templates/loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id, Model model) {
        Employee emp = hrRepository.getEmployeeById(id);
        model.addAttribute("emp", emp);

        return "hr_templates/dashboard";
    }

    // Add employee
    @GetMapping("/add_employee")
    String addEmployee(Model model) {
        model.addAttribute("employee",new Employee(0, null, null, 0, 0));
        return "hr_templates/add_employee_template";
    }

    @PostMapping("/add_employee")
    String submitEmployee(@ModelAttribute Employee employee) { //, @RequestParam int id, @RequestParam String name, @RequestParam String designation,@RequestParam int salary, @RequestParam float exp) {
        hrRepository.addEmployee(employee);

        return "hr_templates/add_employee_template";
    }

    @GetMapping("/logout")
    String logout() {

        return "homepage";
    }
}