package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.TeacherJournalDTO;
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
    public TeacherJournalDTO getConections(@PathVariable("teacher_id") final int teacher_id,
                                           @PathVariable("class_id") final int class_id,
                                           @PathVariable("subject_id") final int subject_id)
    {
        TeacherJournalDTO teacherJournalDTO = new TeacherJournalDTO();
        for (int i = 0; i < list.size(); i ++)
        {
            if (list.get(i).getTeacher_id() == teacher_id && list.get(i).getSubject_id() == subject_id
                    && list.get(i).getClass_id() == class_id) teacherJournalDTO = list.get(i);
        }
        return teacherJournalDTO;
    }

    @ApiOperation(value = "Connects a teacher with a journal")
    @PostMapping("/teachers/{teacher_id}/classes/{class_id}/subjects/{subject_id}/journal")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "Teacher successfully added to the journal"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public TeacherJournalDTO postConection(@PathVariable("teacher_id") final int teacher_id,
                                           @PathVariable("class_id") final int class_id,
                                           @PathVariable("subject_id") final int subject_id)//creates connection
    {
        list.add(new TeacherJournalDTO(teacher_id, class_id, subject_id, 2018));

        return list.get(list.size() - 1); //get new connection
    }


}
