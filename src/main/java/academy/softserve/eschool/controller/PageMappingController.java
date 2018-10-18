package academy.softserve.eschool.controller;

import org.springframework.stereotype.Controller;
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
    public String journals(){
        return "/journalsList.html";
    }
}

