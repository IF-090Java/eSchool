package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.AddTeacherToJournalDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mariana on 12.10.2018.
 */

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Adds a teacher to a journal")
public class AddTeacherToJournalController {

    //Many To Many (a teacher can be conected with many journals and a journal contains many teachers)

    /*@Query(value = "INSERT INTO classbook(id_grade, id_subject, id_teacher) VALUES (SELECT id FROM grade " +
           "WHERE gname = 'AddTeacherToJournalDTO.theClass'), (SELECT id FROM subject " +
           "WHERE sname = 'AddTeacherToJournalDTO.subject'), (SELECT id FROM teacher " +
           "WHERE first_name ...)")
    */

    @ApiOperation(value = "Shows all the teachers with their classes and subjects")
    @GetMapping("/teachers/journal")//this method can be deleted
    public List<AddTeacherToJournalDTO> getAddTeacherToJournalDTOList() {
        return Arrays.asList(
                new AddTeacherToJournalDTO("Гавука Надія Іванівна", "10-A", "Математика"),
                new AddTeacherToJournalDTO("Пасічна Ганна Миколаївна", "11-A", "Українська мова")
        );
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Вчителька успішно додана до журналу"),
                    @ApiResponse(code = 500, message = "Помилка сервера")
            }
    )
    @PostMapping("/teachers/{id1}/classes/{id2}/subjects/{id3}/journal")
    public AddTeacherToJournalDTO postConection(@PathVariable("id1") final String id1, @PathVariable("id2") final String id2, @PathVariable("id3") final String id3)
    //add a teacher with id to a journal of the class with id to a subject whith id
    {
        return getAddTeacherToJournalDTOList().get(0);//example
    }

}
