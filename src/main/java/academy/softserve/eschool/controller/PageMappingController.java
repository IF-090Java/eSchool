package academy.softserve.eschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageMappingController {
    @RequestMapping("classes/{id}/edit")
    public String editClassPage(){
        return "/editClass.html";
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

    @RequestMapping("/teachers/add")
    public String addTeacher(){
        return "/teacher/addTeacher.html";
    }

    @RequestMapping("/students/add")
    public String addStudent(){
        return "/student/addStudent.html";
    }

    @RequestMapping("teachers/profile")
    public String teacherProfile(){
        return "/teacher/Profile.html";
    }

    @RequestMapping("students/profile")
    public String studentProfile(){
        return "/student/Profile.html";
    }

    @RequestMapping("/teachers/add/journal")
    public String teacherJournalPage(){
        return "/TeacherJournal.html";
    }

    @RequestMapping("/schedule/create")
    public String schedulePage(){ return "/schedule.html"; }
}

