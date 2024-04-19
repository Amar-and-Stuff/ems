package com.empsys.ems.hr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
public class HRController {
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }
}