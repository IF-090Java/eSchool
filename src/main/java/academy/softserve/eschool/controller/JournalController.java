package academy.softserve.eschool.controller;

import academy.softserve.eschool.dto.Journal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {
    @GetMapping("")
    public List<Journal> getJournals(){
        List<Journal> list = new ArrayList<>();
        list.add(new Journal("Фізика","7-A"));
        list.add(new Journal("Математика","5-Б"));
        list.add(new Journal("Хімія","5-Б"));
        list.add(new Journal("Українська мова","6-Б"));
        list.add(new Journal("Фізика","5-А"));
        return list;
    }
}
