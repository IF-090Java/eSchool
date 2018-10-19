package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.SubjectDTO;
import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Adds a teacher to a journal")
public class TeacherJournalController {
    //Many To Many (a teacher can be conected with many journals and a journal contains many teachers)

    private static List<TeacherJournalDTO> list = new ArrayList<>();

    static {
        list.add(new TeacherJournalDTO(new TeacherNamesDTO(1,"Іван","Петрович"), new ClassDTO(1,"5-A","Класний керівник - Данилишин Богдан"),
                        new SubjectDTO(1, "Історія України")));
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    public TeacherJournalDTO postConection(@PathVariable("teacher_id") final int teacher_id,
                                           @PathVariable("class_id") final int class_id,
                                           @PathVariable("subject_id") final int subject_id)
    //add a teacher with id to a journal of the class with id to a subject whith id
    {
        list.add(new TeacherJournalDTO(new TeacherNamesDTO(teacher_id,"Іван","Петрович"), new ClassDTO(class_id,"5-Б","desc"),
                new SubjectDTO(subject_id, "Інформатика")));

        return list.get(list.size() - 1); //get new connection
    }


}
