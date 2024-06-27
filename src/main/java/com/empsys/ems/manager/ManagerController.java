package com.empsys.ems.manager;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/login")
    String employeeLogin() {
        return "manager_templates/loginpage";
    }

    @PostMapping("/login")
    String hRLoginSubmission(@RequestParam int id, @RequestParam String password,Model model) {
        Employee emp = managerRepository.getReferenceById(id);
        if (emp.getDesignation().equals("Manager")) {
            return "redirect:/manager/" + emp.getId();
        }
        else {
            model.addAttribute("message","You Cannot Login Here");
        }
        return "manager_templates/loginpage";
    }

    @GetMapping("/{id}")
    String dashboard(@PathVariable int id,Model model) {
        Employee manager=managerRepository.getReferenceById(id);
        model.addAttribute("manager",manager);
        return "manager_templates/dashboard";
    }
    @GetMapping("/manage/{id}")
    String manage(@PathVariable int id,Model model) {
        List<Employee> employees=managerRepository.findAll();
        model.addAttribute("manager_id",id);
        model.addAttribute("data",employees);
        return "manager_templates/management";
    }

    @GetMapping("/manage/{manager_id}/")
    String updateEmployee(@PathVariable int manager_id, @RequestParam(name = "employee_id", required = true) int employee_id, Model model) {
        model.addAttribute("manager_id", manager_id);
        model.addAttribute("employee_id", employee_id);
        model.addAttribute("employee", managerRepository.getReferenceById(employee_id));
        return "manager_templates/update_employee";
    }

    @PostMapping("/manage/{manager_id}/")
        String updateEmployeeSubmisssion(@PathVariable int manager_id, @RequestParam(name = "employee_id", required = true) int employee_id,@ModelAttribute Employee employee,  Model model) {
            managerRepository.save(employee);
            return "manager_templates/update_employee";
        }
    @GetMapping("/manage/delete/{manager_id}/")
    String deleteEmployee(@PathVariable int manager_id,@RequestParam(name="employee_id",required=true) int employee_id,Model model) {
        managerRepository.deleteById(employee_id);
        model.addAttribute("manager_id", manager_id);
        return "redirect:/manager/manage/"+manager_id;
    }
    @GetMapping("/logout")
    String logout() {
        return "redirect:/";
    }
}
