package com.empsys.ems.hr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empsys.ems.employee.Employee;

@Repository
public interface HRRepository extends JpaRepository<Employee, Integer> {}