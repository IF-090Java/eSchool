package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classes")
@Api(value = "classes", description = "Endpoints for classes")
public class ClassController {
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create class")
    @PostMapping
    public ClassDTO addClass(@RequestBody ClassDTO newClass){
        return newClass;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class", response = ClassDTO.class)
    @GetMapping("/{id}")
    public ClassDTO getClassById(@PathVariable String id){
        return new ClassDTO(1,"8-Б", "Класний керівник - Кашуба Григорій");
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class", response = ClassDTO.class)
    @GetMapping()
    public List<ClassDTO> getAll(){
        List<ClassDTO> list = new ArrayList<>();
        list.add(new ClassDTO(1,"5-A","desc"));
        list.add(new ClassDTO(2,"5-Б","desc"));
        list.add(new ClassDTO(3,"5-В","desc"));
        list.add(new ClassDTO(4,"6-A","desc"));
        list.add(new ClassDTO(5,"6-Б","desc"));
        list.add(new ClassDTO(6,"7-А","desc"));
        return list;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Update class")
    @PutMapping("/{id}")
    public ClassDTO editClass(@PathVariable String id, @RequestBody ClassDTO editClass){
        return editClass;
    }
}