package com.catschool.ejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @TableGenerator(name="department_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val")
    @Id
    @GeneratedValue(generator = "department_gen", strategy = GenerationType.TABLE)
    private long departmentId;
    private String departmentName;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments", cascade = CascadeType.PERSIST)
    private List<Employee> employees;

    public Department(String departmentName, List<Employee> employees) {
        this.departmentName = departmentName;
        this.employees = employees;
    }

    public Department() {
    }

    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
