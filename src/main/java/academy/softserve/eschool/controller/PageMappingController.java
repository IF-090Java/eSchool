package academy.softserve.eschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageMappingController {
    @RequestMapping("classes/{id}/edit")
    public String editClassPage(){
        return "/editClass.html";
    }
    
    @RequestMapping("diary/{studentId}")
    public String diaryPage() {
    	return "/diary.html";
    }
    
    @RequestMapping("stats")
    public String statisticsPage() {
    	return "/statistics.html";
    }
}

