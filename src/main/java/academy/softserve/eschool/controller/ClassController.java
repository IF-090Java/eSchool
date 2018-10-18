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
    private static List<ClassDTO> list = new ArrayList<>();
    static {
        list.add(new ClassDTO(1,"5-A","Класний керівник - Данилишин Богдан"));
        list.add(new ClassDTO(2,"5-Б","Класний керівник - Вакун Оксана"));
        list.add(new ClassDTO(3,"5-В","Класний керівник - Фігурка Марія"));
        list.add(new ClassDTO(4,"6-A","Класний керівник - Семчишин Олег"));
        list.add(new ClassDTO(5,"6-Б","Класний керівник - Козин Лариса"));
        list.add(new ClassDTO(6,"7-А","Класний керівник - Баран Ярослав"));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Create class")
    @PostMapping
    public boolean addClass(@RequestBody ClassDTO newClass){
        newClass.setId(7);
        list.add(newClass);
        return true;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class", response = ClassDTO.class)
    @GetMapping("/{id}")
    public ClassDTO getClassById(@PathVariable int id){
        for(ClassDTO classDTO : list){
            if (classDTO.getId() == id) return classDTO;
        }
        return new ClassDTO(1,"8-Б", "Класний керівник - Кашуба Григорій");
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad data"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Get Class", response = ClassDTO.class)
    @GetMapping()
    public List<ClassDTO> getAll(){
        return list;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation("Update class")
    @PutMapping("/{id}")
    public boolean editClass(@PathVariable int id, @RequestBody ClassDTO editClass){
        for (ClassDTO classDTO : list){
            if (classDTO.getId() == id){
                classDTO.setClassName(editClass.getClassName());
                classDTO.setClassDescription(editClass.getClassDescription());
                list.set(classDTO.getId()-1, classDTO);
                return true;
            }
        }
        return false;
    }
}