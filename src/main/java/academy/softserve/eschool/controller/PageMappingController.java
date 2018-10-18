package academy.softserve.eschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageMappingController {
    @RequestMapping("classes/{id}/edit")
    public String editClassPage(){
        return "/editClass.html";
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

    @RequestMapping("subjectList")
    public String subjectList(){
        return "/subjectsList.html";
    }

    @RequestMapping("subjects/{id}/edit")
    public String editSubjectPage(){
        return "/editSubject.html";
    }

    @RequestMapping("subjects/add")
    public String addSubjectPage(){
        return "/addSubject.html";
    }

    @RequestMapping("students/class/{id}")
    public String studentByClass(){
        return "/studentClass.html";
    }

    @RequestMapping("studentsList")
    public String listClasses(){
        return "/chooseClass.html";
    }

    @RequestMapping("students/{id}/edit")
    public String editStudent(){
        return "/editStudent.html";
    }

    @RequestMapping("teachersList")
    public String teacherList(){
        return "/teacherslist.html";
    }

    @RequestMapping("teachers/{id}/edit")
    public String editTeacher(){
        return "/editTeacher.html";
    }

}

