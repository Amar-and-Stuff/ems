package com.empsys.ems.manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {
    public List<Manager> managers;
    ManagerRepository() {
        managers=new ArrayList<>();
        managers.add(new Manager(1, "Mounish", "Employee", 1000, 0));
        managers.add(new Manager(2, "Amar", "Employee", 1000, 0));
        managers.add(new Manager(3, "Kiran", "Employee", 1000, 0));
        managers.add(new Manager(4, "Dhanush", "Employee", 1000, 0));
        managers.add(new Manager(5, "Karthik", "Employee", 1000, 0));
    }   
    public Manager getManagerById(int id) {
        return managers.stream().filter(emp->emp.getID()==id).findFirst().get();
    } 
}
