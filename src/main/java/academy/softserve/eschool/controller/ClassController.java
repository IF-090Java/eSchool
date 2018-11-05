package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'TEACHER')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'TEACHER')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    public List<ClassDTO> getActiveClassesWithStudents(){
        return classService.getActiveClassesWithStudents();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get active classes without students")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idClass}")
    public ClassDTO editClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass,
            @ApiParam(value = "object of class", required = true) @RequestBody ClassDTO editClass){
        classService.updateClass(idClass, editClass);
        return editClass;
    }
}