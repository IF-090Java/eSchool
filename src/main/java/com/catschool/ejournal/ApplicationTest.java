package com.catschool.ejournal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ApplicationTest {

    @GetMapping
    public String message(){
        return "Hello World!";
    }
}
