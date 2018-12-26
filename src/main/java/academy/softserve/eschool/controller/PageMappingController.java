package academy.softserve.eschool.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;


@Controller
@ApiIgnore
@RequestMapping("/ui/")
@Secured("permitAll")
public class PageMappingController {
    @RequestMapping("classes/{id}/edit")
    public String editClassPage() {
        return "/addEditClass.html";
    }

    @RequestMapping("journals/subjects/{idSubject}/classes/{idClass}/view")
    public String journal() {
        return "/journal.html";
    }

    @RequestMapping("journalsList/view")
    public String journals() {
        return "/journalsList.html";
    }

    @RequestMapping("classes/classList")
    public String getClassListShowStudents() {
        return "/classList.html";
    }

    @RequestMapping("journals/classList")
    public String getClassListShowJournals() {
        return "/classList.html";
    }

    @RequestMapping("classes/list")
    public String getClassesListForEdit() {
        return "/classList.html";
    }

    @RequestMapping("/classes/add")
    public String addClassPage() {
        return "/addEditClass.html";
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
    public String addTeacher() {
        return "/teacher/addTeacher.html";
    }

    @RequestMapping("students/add")
    public String addStudent() {
        return "/student/addStudent.html";
    }

    @RequestMapping("teachers/{id}/profile")
    public String teacherProfile(@PathVariable int id) {
        return "/teacher/Profile.html";
    }

    @RequestMapping("journals/class/{idClass}")
    public String journalsByClass(@PathVariable int idClass) {
        return "/journalsForClass.html";
    }

    @RequestMapping("students/{id}/profile")
    public String studentProfile(@PathVariable int id) {
        return "/student/Profile.html";
    }

    @RequestMapping("admin/stats")
    public String statsForAdmin() {
        return "/adminStats.html";
    }

    @RequestMapping("teachers/add/journal")
    public String teacherJournalPage() {
        return "/TeacherJournal.html";
    }

    @RequestMapping("schedule/create")
    public String schedulePage() {
        return "/schedule.html";
    }

    @RequestMapping("subjectList")
    public String subjectList() {
        return "/subjectsList.html";
    }

    @RequestMapping("subjects/{id}/edit")
    public String editSubjectPage() {
        return "/addEditSubject.html";
    }

    @RequestMapping("subjects/add")
    public String addSubjectPage() {
        return "/addEditSubject.html";
    }

    @RequestMapping("students/class/{id}")
    public String studentByClass() {
        return "/studentClass.html";
    }

    @RequestMapping("studentsList")//?????
    public String listClasses() {
        return "/chooseClass.html";
    }

    @RequestMapping("students/{id}/edit")
    public String editStudent() {
        return "/editTeacher.html";
    }

    @RequestMapping("students/new-year-transition")
    public String transitionToNewYear(){
        return "/transToNewYear.html";
    }

    @RequestMapping("teachersList")
    public String teacherList(){
        return "/teacherslist.html";
    }

    @RequestMapping("teachers/{id}/edit")
    public String editTeacher(){
        return "/editTeacher.html";
    }

    @RequestMapping("student/{id}/edit")
    public String edit_Student(){
        return "/editTeacher.html";
    }

    @RequestMapping("teacher/{id}/edit")
    public String edit_Teacher(){
        return "/editTeacher.html";
    }

    @RequestMapping("admin")
    public String adminHome(){
        return "/admin-home.html";
    }

    @RequestMapping("login")
    public String getLoginPage(){
        return "/loginForm.html";
    }
    
    @RequestMapping("requestPasswordReset")
    public String getRequestPasswordRecoveryPage() {
        return "/passwordResetRequest.html";
    }
    
    @RequestMapping("resetPassword")
    public String getResetPasswordPage() {
        return "/passwordReset.html";
    }

    @RequestMapping("mark_types/list")
    public String getMarkTypes() {
        return "/MarkTypesList.html";
    }

    @RequestMapping("mark_types/add")
    public String addMarkTypes() {
        return "/AddEditMarkType.html";
    }

    @RequestMapping("mark_types/{id}/edit")
    public String editMarkTypes() {
        return "/AddEditMarkType.html";
    }

}

