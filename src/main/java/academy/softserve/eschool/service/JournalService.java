package academy.softserve.eschool.service;


import academy.softserve.eschool.dto.HomeworkDTO;
import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;

import java.util.List;

public interface JournalService{
    List<JournalDTO> getJournalsByTeacher(int idTeacher);
    List<JournalDTO> getActiveJournalsByTeacher(int idTeacher);
    List<JournalDTO> getJournals();
    List<JournalMarkDTO> getJournal(int idSubject, int idClass);
    List<HomeworkDTO> getHomework(int idSubject, int idClass);
}
