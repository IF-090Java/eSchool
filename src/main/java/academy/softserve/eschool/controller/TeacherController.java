package academy.softserve.eschool.controller;

import academy.softserve.eschool.DTOs.TeacherDTO;
import academy.softserve.eschool.DTOs.TeacherNamesDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @GetMapping("")
    public List<TeacherNamesDTO> getall(){
        List<TeacherNamesDTO> list = new ArrayList<>();
        list.add(new TeacherNamesDTO(1,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(2,"Іван", "Петрович"));
        list.add(new TeacherNamesDTO(3,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(4,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(5,"Іван","Петрович"));
        list.add(new TeacherNamesDTO(6,"Іван","Петрович"));
        return list;
    }

    @GetMapping("/{id}")
    public TeacherDTO getTeacher(){
        return new TeacherDTO("Іван","Іванов","вавава","*******","vanya@mail","05050505056");
    }
    @PutMapping("/{id}")
    public void updateTeacher(@RequestBody TeacherDTO teacher,@PathVariable String id){
        // someservice.update(id,TeacherDTO)
    }



}
