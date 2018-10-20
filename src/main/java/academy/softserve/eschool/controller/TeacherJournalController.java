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

//END POINTS  /teachers/{id}/classes/{id}/subjects/{id}/journal
//            /teachers/journal/connection

@RestController
@RequestMapping("")
@Api(value = "Teacher's Endpoint", description = "Connects a teacher with a journal")
public class TeacherJournalController {
    //Many To Many (a teacher can be conected with many journals and a journal contains many teachers)

    private static List<TeacherJournalDTO> list = new ArrayList<>();

    static {
        list.add(new TeacherJournalDTO(1, 2, 3, 2018));
    }

    @ApiOperation(value = "Gets a teacher with a journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @GetMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    public TeacherJournalDTO getConection(@PathVariable("teacher_id") final int teacher_id,
                                           @PathVariable("class_id") final int class_id,
                                           @PathVariable("subject_id") final int subject_id)
    //gets a teacher with id connected to a journal
    {
        list.add(new TeacherJournalDTO(teacher_id, class_id, subject_id, 2018));

        return list.get(list.size() - 1); //get new connection
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PostMapping("/teachers/journal/connection")
    public TeacherJournalDTO postConection(@RequestBody TeacherJournalDTO teacherJournalDTO)
    //creates connection
    {
        list.add(teacherJournalDTO);
        return teacherJournalDTO;
    }


}
