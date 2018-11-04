package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/classes")
@Api(value = "classes", description = "Endpoints for classes")
public class ClassController {
    @Autowired ClassServiceImpl classService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create class")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ClassDTO addClass(@RequestBody ClassDTO newClassDTO){
        classService.saveClass(newClassDTO);
        return newClassDTO;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class", response = ClassDTO.class)
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    @GetMapping("/{id}")
    public ClassDTO getClassById(@PathVariable int id){
        return classService.findClassById(id);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get classes list with active status", response = ClassDTO.class)
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    @GetMapping
    public List<ClassDTO> getActiveClasses(){
        return classService.findClassesByStatus(true);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get true active classes with students", response = ClassDTO.class)
    @PermitAll
    @GetMapping("/active")
    public List<ClassDTO> getActiveClassesWithStudents(){
        return classService.getActiveClassesWithStudents();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })

    @ApiOperation(value = "Get active classes without students", response = ClassDTO.class)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active/students/none")
    public List<ClassDTO> getActiveClassesWithoutStudents() {
        return classService.getActiveClassesWithoutStudents();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get inactive classes list", response = ClassDTO.class)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/inactive")
    public List<ClassDTO> getNonActiveClasses(){
        return classService.findClassesByStatus(false);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Update class")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClassDTO editClass(@PathVariable int id, @RequestBody ClassDTO editClass){
        classService.updateClass(id, editClass);
        return editClass;
    }
}