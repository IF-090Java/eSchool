package com.catschool.ejournal.Controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationTest {

    @RequestMapping("/test")
    public String message() {
        return "Objects saved!";
    }

    @RequestMapping("/employees/{id}")
    public void showEmployee(@PathVariable("id") int id){

    }

    @RequestMapping("/employees")
    public void showEmployees(){

    }
}
