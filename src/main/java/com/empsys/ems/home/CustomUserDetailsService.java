package com.empsys.ems.home;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empsys.ems.employee.Employee;
import com.empsys.ems.employee.EmployeeRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    private EmployeeRepository empRepo;
    CustomUserDetailsService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = empRepo.getReferenceById(Integer.parseInt(username));

        return new CustomUserPrinciple(employee);
    }

}
