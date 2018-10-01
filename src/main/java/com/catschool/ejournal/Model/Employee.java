package com.catschool.ejournal.Model;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id")
    private int employeeId;

    @Column(name = "name", nullable = false)
    private String employeeName;

    @OneToOne
    @JoinColumn(name = "id")
    private Department employeeDepartment;

    public Employee(int employeeId, String employeeName, Department employeeDepartment) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
