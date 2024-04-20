package com.empsys.ems.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private ManagerRepository managerRepository;
    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }
    @GetMapping("/{id}")
    String dashboard(@PathVariable int id,Model model) {
        Manager manager=managerRepository.getManagerById(id);
        model.addAttribute(manager);
        return "/manager_templates/dashboard";
    }
    @GetMapping("/manage/{id}")
    String manage(@PathVariable int id,Model model) {
        // Manager manager=managerRepository.getManagerById(id);
        // model.addAttribute("mr",mr);
        Manager manager=managerRepository.getManagerById(id);
        model.addAttribute(manager);
        return "/manager_templates/management";
    }
    @GetMapping("/logout")
    String logout() {
        return "homepage";
    }
}
