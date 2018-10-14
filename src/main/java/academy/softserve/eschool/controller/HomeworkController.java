package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.Homework;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/homeworks")
public class HomeworkController {
    @GetMapping("/subjects/{id}/classes/{id}")
    public List<Homework> getHomeworks(){
        List<Homework> list = new ArrayList<>();
        list.add(new Homework(new Date(),"p.125","file1.txt"));
        list.add(new Homework(new Date(),"p.122","file2.txt"));
        list.add(new Homework(new Date(),"ex.2","file3.txt"));
        list.add(new Homework(new Date(),"reading","file4.txt"));
        list.add(new Homework(new Date(),"p.12 , ex.2","file5.txt"));
        return list;
    }
}
