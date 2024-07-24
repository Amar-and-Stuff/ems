package com.empsys.ems.employee;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private AuthenticationManager authenticationManager;

    public EmployeeController(EmployeeRepository employeeRepository, AuthenticationManager authenticationManager) {
        this.employeeRepository = employeeRepository;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("") 
    String homePage() {
        return "homepage";
    }

    // login
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }

    @PostMapping("/login")
    String employeeLoginSubmission(@RequestParam int id, @RequestParam String password) {
        System.out.println("What is even happening.");
        Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken("3","1234"));

        if (authentication.isAuthenticated()) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    String dashboard(Model model) { // can also add Authentication in the method parameters.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = Integer.parseInt(auth.getName());
        Employee emp = employeeRepository.getReferenceById(id);
        model.addAttribute("emp", emp);
        System.out.println("Logging here for username which will be a number anyway " + auth.getName());
        return "profile";
    }

    @GetMapping("/logout")
    String logout() {
        return "redirect:/";
    }
}
