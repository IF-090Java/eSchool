package academy.softserve.eschool.service;


import academy.softserve.eschool.dto.JournalDTO;

import java.util.List;

public interface JournalService{
    List<JournalDTO> getSubjectsByTeacher(int idTeacher);
}
