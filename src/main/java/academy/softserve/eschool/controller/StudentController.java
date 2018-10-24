package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.EditStudentDTO;
import academy.softserve.eschool.dto.StudentDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.StudentRepository;
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
@RequestMapping("/students")
@Api(description = "Student controller")
public class StudentController {

    private StudentRepository studentRepository;
    private static List<StudentDTO> list = new ArrayList<>();

    static {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        try {
        list.add(new StudentDTO(1, "Василь", "Cемків", "Іванович", "6-а", dateformat.parse("2006-10-15"), "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(2, "Віктор", "Романчук", "Андрійович", "7-б,", dateformat.parse("2007-10-15"), "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(3, "Ігор", "Кривенчук", "Іванович", "5-a", dateformat.parse("2004-10-15"), "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(4, "Вікторія", "Приймак", "Петрович", "7-a", dateformat.parse("2007-10-15"), "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        list.add(new StudentDTO(5, "Ольга", "Семенів", "Іванівна", "5-а", dateformat.parse("2004-10-15"), "stud.john.doe", "john.doe@email.com", "09xxxxxxxx"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @PostMapping
    @ApiOperation(value = "Add student, first name, last name and class passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "student crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public StudentDTO addStudent(@RequestBody StudentDTO student) {
        return student;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get student")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    @SneakyThrows
    public StudentDTO getStudent(@PathVariable int id) {
//        Student student = studentRepository.getOne(id);
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setFirstname(student.getFirstName());
//        studentDTO.setLastname(student.getLastName());
//        studentDTO.setPatronymic(student.getPatronymic());
//        studentDTO.setDateOfBirth(student.getDateOfBirth());
//        for (Clazz clazz :student.getClasses()) {
//            if (clazz.isActive())
//                studentDTO.setClasse(clazz.getName());
//        }
//        studentDTO.setLogin(student.getLogin());
//        studentDTO.setEmail(student.getEmail());
//        studentDTO.setPhone(student.getPhone());

        for (StudentDTO studentDTO1 : list){
            if (studentDTO1.getId()==id) return studentDTO1;
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        StudentDTO studentDTO1 = new StudentDTO(1,"Ірина", "Самійлів", "Петрівна", "7-b", dateformat.parse("2003-01-15"), "stud.johnohn.doe", "john.doe@email.com", "09xxxxxxxx");
        return studentDTO1;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update profile of student")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateStudent(@RequestBody EditStudentDTO student, @PathVariable String id){
        // someservice.update(id,student)
    }
    @GetMapping("/classes/{id}")
    @ApiOperation(value = "get students from class")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "student found and passed"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public List<StudentDTO> getStudentsByClass(@PathVariable int id){
        return list;
    }
}
