package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.TeacherJournalDTO;
import academy.softserve.eschool.model.ClassTeacherSubjectLink;

/**
 * This is an interface that the {@link ClassTeacherSubjectServiceImpl} implements
 *
 * @author Mariana Vorotniak
 */
public interface ClassTeacherSubjectService {

    ClassTeacherSubjectLink saveClassTeacherSubject(TeacherJournalDTO teacherJournalDTO, boolean isActive);
}
