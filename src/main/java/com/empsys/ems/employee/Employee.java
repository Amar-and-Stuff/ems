package com.empsys.ems.employee;

public class Employee {
   private int id;
   private String name;
   private Designation designation;
   private int salary;
   private float exp;
public Employee(int id, String name, Designation designation, int salary, float exp) {
    this.id = id;
    this.name = name;
    this.designation = designation;
    this.salary = salary;
    this.exp = exp;
}
public int getID() {
    return id;
}

public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public Designation getDesignation() {
    return designation;
}
public void setDesignation(Designation designation) {
    this.designation = designation;
}
public int getSalary() {
    return salary;
}
public void setSalary(int salary) {
    this.salary = salary;
}
public float getExp() {
    return exp;
}
public void setExp(float exp) {
    this.exp = exp;
}
@Override
public String toString() {
    return "Employee [ID=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary + ", exp="
            + exp + "]";
}
   
}
