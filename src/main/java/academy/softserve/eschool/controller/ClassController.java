package academy.softserve.eschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.service.ClassServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
        return classService.saveClass(newClassDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'TEACHER')")
    @GetMapping("/{id}")
    public ClassDTO getClassById(
            @ApiParam(value = "id of class", required = true) @PathVariable int id){
        return classService.findClassById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get all classes")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'TEACHER')")
    @GetMapping
    public List<ClassDTO> getAllClasses(
    		@ApiParam(value="only classes that study specified subject will be returned") @RequestParam(required=false) Integer subjectId){
        if (subjectId == null) {
        	return classService.getAllClasses();
        } else {
        	return classService.getClassesBySubject(subjectId);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Class successfully updated"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Update class")
    @PreAuthorize("hasRole('ADMIN')")
    //todo bk ++ it's better to name param as classId instead. But I'd name it as 'id' in current case.
    //todo bk It's too obvious that 'classId' belongs to the class. But in case you have few ids then name it properly
    @PutMapping("/{id}")
    public ClassDTO editClass(
            @ApiParam(value = "id of class", required = true) @PathVariable int id,
            @ApiParam(value = "object of class", required = true) @RequestBody ClassDTO editClass){
        //todo bk ++ updating objects with native queries bring a lot af mess into the app. And it's hard to support them. Use entity and repository for it.

        return classService.updateClass(id, editClass);
    }
}