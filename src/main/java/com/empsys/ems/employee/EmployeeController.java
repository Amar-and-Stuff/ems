package com.empsys.ems.employee;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/") 
    String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("is authenticated "+authentication.isAuthenticated() + " as " + authentication.getName());
        model.addAttribute("name", authentication.getName());
        return "homepage";
    }

    // login
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }

    @GetMapping("/profile")
    String dashboard(Model model) { // can also add Authentication in the method parameters.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = Integer.parseInt(auth.getName());
        Employee emp = employeeRepository.getReferenceById(id);
        model.addAttribute("emp", emp);
        return "profile";
    }

    // @GetMapping("/logout")
    // String logout() {
    //     return "redirect:/";
    // }
}
