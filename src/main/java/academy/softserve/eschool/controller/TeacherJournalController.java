package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mariana on 12.10.2018.
 */

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Adds a teacher to a journal")
public class TeacherJournalController {

    //Many To Many (a teacher can be conected with many journals and a journal contains many teachers)

    /*@Query(value = "INSERT INTO classbook(id_grade, id_subject, id_teacher) VALUES (SELECT id FROM grade " +
           "WHERE gname = 'AddTeacherToJournalDTO.theClass'), (SELECT id FROM subject " +
           "WHERE sname = 'AddTeacherToJournalDTO.subject'), (SELECT id FROM teacher " +
           "WHERE first_name ...)")
    */

    @ApiOperation(value = "Shows all the teachers with their classes and subjects")
    public List<TeacherJournalDTO> getTeacherJournalDTOList() {
        return Arrays.asList(
                new TeacherJournalDTO(new TeacherNamesDTO(1,"Іван","Петрович"), new ClassDTO(1,8, "Б", "Класний керівник - Кашуба Григорій"), "Математика"),
                new TeacherJournalDTO(new TeacherNamesDTO(2,"Іван","Петрович"), new ClassDTO(2,9, "Б", "Класний керівник - Кашуба Григорій"), "Українська мова")
        );
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    public TeacherJournalDTO postConection(@PathVariable("teacher_id") final Long teacher_id, @PathVariable("class_id") final Long class_id,
                                           @PathVariable("subject_id") final Long subject_id)
    //add a teacher with id to a journal of the class with id to a subject whith id
    {
        return getTeacherJournalDTOList().get(0);//example
    }

}
