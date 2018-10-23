package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditTeacherDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.model.Teacher;
import academy.softserve.eschool.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
public class TeacherController {
    private TeacherRepository teacherRepository;

    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
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
  
    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "teacher crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacher) {
        return teacher;
    }
  
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @SneakyThrows
    public TeacherDTO getTeacher(@PathVariable int id){
        Teacher teacher = teacherRepository.getOne(id);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstname(teacher.getFirstName());
        teacherDTO.setLastname(teacher.getLastName());
        teacherDTO.setPatronymic(teacher.getPatronymic());
        teacherDTO.setDateOfBirth(teacher.getDateOfBirth());
        teacherDTO.setLogin(teacher.getLogin());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setPhone(teacher.getPhone());

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");

        TeacherDTO teacherDTO1 = new TeacherDTO(1,"Іван","Якимів", "Петрович",dateformat.parse("1999-11-11"),"вавава","*******","vanya@mail","05050505056");

        return teacherDTO1;
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateTeacher(@RequestBody EditTeacherDTO teacher, @PathVariable int id){
        // someservice.update(id,teacher)
    }



}

