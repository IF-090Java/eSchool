package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;

/**
 * This is an interface that the {@link ClassTeacherSubjectServiceImpl} implements
 *
 * @author Mariana Vorotniak
 */
public interface ClassTeacherSubjectService {

    void saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive);
}
