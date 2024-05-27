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
    String hRLoginSubmission(@RequestParam int id, @RequestParam String password,Model model) {
        Employee emp = hrRepository.getReferenceById(id);
        if (emp.getDesignation().equals("HR")) {
            return "redirect:/hr/" + emp.getId();
        }
        else {
            model.addAttribute("message","You Cannot Login Here");
        }
        return "hr_templates/loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id, Model model) {
        Employee emp = hrRepository.getReferenceById(id);
        model.addAttribute("emp", emp);
        model.addAttribute("hr_id", id);
        return "hr_templates/dashboard";
    }

    // Add employee
    @GetMapping("/add_employee/{id}")
    String addEmployee(@PathVariable int id, Model model) {
        model.addAttribute("hr_id", id);
        model.addAttribute("employee",new Employee());
        return "hr_templates/add_employee_template";
    }

    @PostMapping("/add_employee/{id}")
    String submitEmployee(@ModelAttribute Employee employee, @PathVariable int id) { //, @RequestParam int id, @RequestParam String name, @RequestParam String designation,@RequestParam int salary, @RequestParam float exp) {
        hrRepository.save(employee);
        return "hr_templates/add_employee_template";
    }

    @GetMapping("/logout")
    String logout() {

        return "redirect:/";
    }
}