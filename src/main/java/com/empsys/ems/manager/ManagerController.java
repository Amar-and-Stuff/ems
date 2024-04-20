package com.empsys.ems.manager;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empsys.ems.employee.Employee;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private ManagerRepository managerRepository;
    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/login")
    String employeeLogin() {
        return "manager_templates/loginpage";
    }

    @PostMapping("/login")
    String hRLoginSubmission(@RequestParam int id, @RequestParam String password) {
        Employee emp = managerRepository.getEmployeeById(id);
        if (emp.getDesignation().equals("Manager")) {
            return "redirect:/manager/" + emp.getId();
        }
        return "manager_templates/loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id,Model model) {
        Manager manager=managerRepository.getEmployeeById(id);
        model.addAttribute(manager);
        return "manager_templates/dashboard";
    }
    @GetMapping("/manage/{id}")
    String manage(@PathVariable int id,Model model) {
        // Manager manager=managerRepository.getManagerById(id);
        // model.addAttribute("mr",mr);
        Employee manager=managerRepository.getEmployeeById(id);
        List<Employee> employees=managerRepository.getEmployeeDataAsList();
        model.addAttribute("manager",manager);
        model.addAttribute("data",employees);
        return "manager_templates/management";
    }

    @GetMapping("/logout")
    String logout() {
        return "homepage";
    }
    
    @GetMapping("/showall")
    @ResponseBody
    List<Employee> showAllEmployees() {
        return managerRepository.getEmployeeDataAsList();
    }
}
