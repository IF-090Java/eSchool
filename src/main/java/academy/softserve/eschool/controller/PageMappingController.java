package academy.softserve.eschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageMappingController {
    @RequestMapping("classes/{id}/edit")
    public String editClassPage(){
        return "/editClass.html";
    }
    
    @RequestMapping("journals/subjects/{idSubject}/classes/{idClass}/view")
    public String journal(){
        return "/journal.html";
    }

    @RequestMapping("journalsList/view")
    public String journals() {
        return "/journalsList.html";
    }

    @RequestMapping("/classes/add")
    public String addClassPage(){
        return "/addClass.html";
    }

    @RequestMapping("diary/{studentId}")
    public String diaryPage() {
    	return "/diary.html";
    }

    @RequestMapping("stats")
    public String statisticsPage() {
    	return "/statistics.html";
    }

    @RequestMapping("teachers/add")
    public String addTeacher(){
        return "/teacher/addTeacher.html";
    }

    @RequestMapping("students/add")
    public String addStudent(){
        return "/student/addStudent.html";
    }

    @RequestMapping("teachers/{id}/profile")
    public String teacherProfile(@PathVariable int id){
        return "/teacher/Profile.html";
    }

    @RequestMapping("students/{id}/profile")
    public String studentProfile(@PathVariable int id){
        return "/student/Profile.html";
    }
}

