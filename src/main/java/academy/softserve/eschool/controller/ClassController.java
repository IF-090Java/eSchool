package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.service.ClassServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classes")
@Api(value = "classes", description = "Endpoints for classes")
public class ClassController {
    @Autowired ClassServiceImpl classService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Class successfully created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create class")
    @PostMapping
    public ClassDTO addClass(
            @ApiParam(value = "class object", required = true) @RequestBody ClassDTO newClassDTO){
        classService.saveClass(newClassDTO);
        return newClassDTO;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class")
    @GetMapping("/{idClass}")
    public ClassDTO getClassById(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass){
        return classService.findClassById(idClass);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get classes with active status")
    @GetMapping
    public List<ClassDTO> getActiveClasses(){
        return classService.findClassesByStatus(true);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get true active classes with students")
    @GetMapping("/active")
    //todo bk TOO MANY endpoints. !!!Do you whnt to make UI team go crazy !!!
    //todo bk make single endpoint that returns all classes with a two  properties that indicate class state and number of students.
    //todo bk the rest endpoints should be deleted.
    public List<ClassDTO> getActiveClassesWithStudents(){
        return classService.getActiveClassesWithStudents();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get active classes without students")
    @GetMapping("/active/students/none")
    public List<ClassDTO> getActiveClassesWithoutStudents() {
        return classService.getActiveClassesWithoutStudents();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get inactive classes list", response = ClassDTO.class)
    @GetMapping("/inactive")
    public List<ClassDTO> getNonActiveClasses(){
        return classService.findClassesByStatus(false);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Class successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Update class")
    //todo bk ++ it's better to name param as classId instead. But I'd name it as 'id' in current case.
    //todo bk It's too obvious that 'classId' belongs to the class. But in case you have few ids then name it properly
    @PutMapping("/{idClass}")
    public ClassDTO editClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass,
            @ApiParam(value = "object of class", required = true) @RequestBody ClassDTO editClass){
        //todo bk ++ updating objects with native queries bring a lot af mess into the app. And it's hard to support them. Use entity and repository for it.

        classService.updateClass(idClass, editClass);
        return editClass;
    }
}