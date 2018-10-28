package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;

public interface ClassTeacherSubjectService {

    void saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive);
}
