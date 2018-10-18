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

import java.util.List;

//END POINT  /teachers/{id}/classes/{id}/subjects/{id}/journal

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Adds a teacher to a journal")
public class TeacherJournalController {
    //Many To Many (a teacher can be conected with many journals and a journal contains many teachers)

    private static List<TeacherJournalDTO> list;

    static {
        list.add(new TeacherJournalDTO(new TeacherNamesDTO(1,"Іван","Петрович"), new ClassDTO(1,"5-A","desc"),
                        new SubjectDTO(1, "Історія України")));
        list.add(new TeacherJournalDTO(new TeacherNamesDTO(2,"Іван","Петрович"), new ClassDTO(2,"5-Б","desc"),
                        new SubjectDTO(2, "Інформатика")));
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
        for (TeacherJournalDTO teacherJournalDTO : list)
        {
            if (teacherJournalDTO.getTeacher().getId() == teacher_id && teacherJournalDTO.getClasss().getId() == class_id &&
                    teacherJournalDTO.getSubject().getId() == subject_id) return null;//already exists
            else
            {
                list.add(new TeacherJournalDTO());
            }
        }
        return list.get(list.size() - 1); //get new connection
    }


}
