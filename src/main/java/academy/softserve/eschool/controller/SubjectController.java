package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.SubjectDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/subjects")
@Api(value = "subjects", description = "API endpoints for subjects")
public class SubjectController {
    private static List<SubjectDTO> list = new ArrayList<>();
    static{
        list.add(new SubjectDTO(1, "Історія України"));
        list.add(new SubjectDTO(2, "Інформатика"));
        list.add(new SubjectDTO(3, "Англійська мова"));
        list.add(new SubjectDTO(4, "Українська мова"));
        list.add(new SubjectDTO(5, "Українська література"));
        list.add(new SubjectDTO(6, "Фізика"));
        list.add(new SubjectDTO(7, "Географія"));
        list.add(new SubjectDTO(8, "Біологія"));
        list.add(new SubjectDTO(9, "Математика"));
        list.add(new SubjectDTO(10, "Хімія"));
    }
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get all subjects", response = SubjectDTO.class)
    @GetMapping()
    public List<SubjectDTO> getAll(){
        return list;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Add new subject")
    @PostMapping
    public SubjectDTO addSubject(@RequestBody SubjectDTO newSubject){
        return newSubject;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Get a subject by Id", response = SubjectDTO.class)
    @GetMapping("/{id}")
    public SubjectDTO getSubjectById(@PathVariable int id){
        return list.get(id-1);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Edit a subject")
    @PutMapping("/{id}")
    public SubjectDTO editSubjectClass(@PathVariable int id, @RequestBody SubjectDTO editSubject){
        return editSubject;
    }
}
