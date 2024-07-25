package com.empsys.ems.manager;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empsys.ems.employee.Employee;
import com.empsys.ems.employee.EmployeeRepository;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private EmployeeRepository managerRepository;

    public ManagerController(EmployeeRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/manage")
    String manage(Model model) {
        int id = Integer.parseInt(this.getAuth().getName());
        List<Employee> employees = managerRepository.findAll();
        model.addAttribute("manager_id", id);
        model.addAttribute("data", employees);
        return "manager_templates/management";
    }

    @GetMapping("/update")
    String updateEmployee(@RequestParam(name = "employee_id", required = true) int employeeId, Model model) {
        int managerId = Integer.parseInt(this.getAuth().getName());
        model.addAttribute("manager_id", managerId);
        model.addAttribute("employee_id", employeeId);
        model.addAttribute("employee", managerRepository.getReferenceById(employeeId));
        return "manager_templates/update_employee";
    }

    @PostMapping("/update")
    String updateEmployeeSubmisssion(@RequestParam(name = "employee_id", required = true) int employee_id, @ModelAttribute Employee employee,
            Model model) {
        managerRepository.save(employee);
        return "redirect:/manager/manage";
    }

    @GetMapping("/delete")
    String deleteEmployee(@RequestParam(name = "employee_id", required = true) int employeeId, Model model) {
        int managerId = Integer.parseInt(this.getAuth().getName());
        managerRepository.deleteById(employeeId);
        model.addAttribute("manager_id", managerId);
        return "redirect:/manager/manage";
    }

    Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
