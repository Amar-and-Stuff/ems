package com.empsys.ems.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManaerController {
    @GetMapping("/login")
    String employeeLogin() {
        return "loginpage";
    }
}
