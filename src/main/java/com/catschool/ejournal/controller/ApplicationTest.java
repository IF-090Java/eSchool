package com.catschool.ejournal.controller;

import com.catschool.ejournal.model.Department;
import com.catschool.ejournal.repository.DepartmentRepository;
import com.catschool.ejournal.repository.EmployeeRepository;
import com.catschool.ejournal.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ApplicationTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String initialize(){
        Department d1 = new Department();
        Department d2 = new Department();
        List<Department> departments = new ArrayList<>();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        List<Employee> employees = new ArrayList<>();

        d1.setDepartmentName("Management");
        d2.setDepartmentName("HR");
        e1.setEmployeeName("Kevin");
        e2.setEmployeeName("Mark");
        departments.add(d1);
        departments.add(d2);
        employees.add(e1);
        employees.add(e2);
        d1.setEmployees(employees);
        d2.setEmployees(employees);
        e1.setDepartments(departments);
        e2.setDepartments(departments);

        employeeRepository.save(e1);
        employeeRepository.save(e2);
        departmentRepository.save(d1);
        departmentRepository.save(d2);

        return "Initialized!";
    }


    @RequestMapping("/employees")
    public List<Employee> showEmployees(){
        return employeeRepository.findAll();
    }

    @RequestMapping("/employees/{id}")
    public Optional<Employee> showEmployee(@PathVariable(value = "id") int id){
        return employeeRepository.findById((long) id);
    }

    @RequestMapping("/departments")
    public List<Department> showDepartments(){
        return departmentRepository.findAll();
    }

    @RequestMapping("/departments/{id}")
    public Optional<Department> showDepartment(@PathVariable(value = "id") int id){
        return departmentRepository.findById((long) id);
    }
}
