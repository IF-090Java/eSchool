package com.catschool.ejournal.Controller;

import com.catschool.ejournal.Model.Department;
import com.catschool.ejournal.Model.Employee;
import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@RestController
public class ApplicationTest {

    @RequestMapping("/test")
    public String message() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ejournal");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Department department = new Department(1, "Marketing");
        entityManager.persist(department);

        Employee employee = new Employee(1, "Kevin", department);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return "Objects saved!";
    }

    @RequestMapping("/employees/{id}")
    public void showEmployee(@PathVariable("id") int id){

    }

    @RequestMapping("/employees")
    public void showEmployees(){

    }
}
