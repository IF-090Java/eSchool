package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/transition")
@Api(value = "transition", description = "Endpoints for transition to new school year")
public class NYTransitionController {
    @Autowired ClassServiceImpl classService;

    @PostMapping
    public Boolean addNewYearClasses(){
        classService.addNewYearClasses();
        return true;
    }

    @GetMapping
    public List<ClassDTO> getActiveClassesWithStudents(){
        return classService.getActiveClassesWithStudents();
    }
}