package academy.softserve.eschool.service;

import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Lesson;
import academy.softserve.eschool.model.Student;
import academy.softserve.eschool.repository.ClassTeacherSubjectLinkRepository;
import academy.softserve.eschool.repository.LessonRepository;
import academy.softserve.eschool.repository.StudentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityExpressionService {
    @NonNull
    private ClassTeacherSubjectLinkRepository classTSLRepository;

    @NonNull
    private LessonRepository lessonRepository;

    @NonNull
    private StudentRepository studentRepository;


    /**
     * Сhecks the teacher teaches in this class or not.
     * @param teacherId teacher's id
     * @param classId class's id
     * @return true if teacher teaches in this class else false.
     */
    public boolean teachesInClass(int teacherId, int classId) {
        return !classTSLRepository.findByTeacherIdAndClazzId(teacherId, classId).isEmpty();

    }

    /**
     * Сhecks the teacher teaches this subject or not.
     * @param teacherId teacher's id
     * @param subjectId subject's id
     * @return true if teacher teaches this subject else false.
     */
    public boolean teachesSubject(int teacherId, int subjectId) {
        return !classTSLRepository.findByTeacherIdAndSubjectId(teacherId, subjectId).isEmpty();
    }

    /**
     * Check by class and subject, has this teacher lesson in this class or not.
     * @param idTeacher teacher's id
     * @param idClass class's id
     * @param idSubject subject's id
     * @return true if teacher have lesson in class else false.
     */
    public boolean hasLessonsInClass(int idTeacher, int idClass, int idSubject) {
        return classTSLRepository.findByTeacherIdAndClazzIdAndSubjectId(idTeacher, idClass, idSubject) != null ? true : false;
    }

    /**
     * Check by class and subject, has this teacher this lesson or not.
     * @param idTeacher teacher's id
     * @param idLesson lesson's id
     * @return true if teacher has lesson in class else false.
     */
    public boolean hasLessonsInClass(int idTeacher, int idLesson) {
        Lesson lesson = lessonRepository.getOne(idLesson);
        return hasLessonsInClass(idTeacher, lesson.getClazz().getId(), lesson.getSubject().getId());
    }


    /**
     * Check by student and class, is this student member of this class or not.
     * @param studentId student's id
     * @param classId class's id
     * @return true if student is member of this class else false
     */
    public boolean isMemberOfClass(int studentId, int classId) {
        boolean isMember = false;
        Student student = studentRepository.getOne(studentId);
        for (Clazz clazz: student.getClasses())
            if (clazz.getId() == classId)
                isMember = true;
        return isMember;
    }
    
    /**
     * Check whether student attends lesson or not
     * @param studentId id of student
     * @param lessonId id of lesson
     * @return true if student attends lesson
     */
    public boolean isAttendingLesson(int studentId, int lessonId) {
        Lesson lesson = lessonRepository.getOne(lessonId);
        return lesson.getClazz().getStudents().stream().anyMatch(s -> s.getId() == studentId);
    }
}
