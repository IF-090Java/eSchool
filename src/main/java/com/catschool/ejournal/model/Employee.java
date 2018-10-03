package com.catschool.ejournal.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @TableGenerator(name="employee_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")
    private long employeeId;
    private String employeeName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_department", joinColumns = @JoinColumn(name = "idemployee"), inverseJoinColumns = @JoinColumn(name = "iddepartment"))
    private List<Department> departments;

    public Employee(String employeeName, List<Department> departments) {
        this.employeeName = employeeName;
        this.departments = departments;
    }

    public Employee() {
    }


    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
