package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.ClassTeacherSubjectLinkId;
import academy.softserve.eschool.service.SecurityExpressionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTeacherSubjectLinkRepository extends JpaRepository<ClassTeacherSubjectLink, ClassTeacherSubjectLinkId> {

    /**
     * Find all journals for a teacher by his id
     * @param idTeacher teacher's id
     * @return {@link ClassTeacherSubjectLink}
     */
    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n" +
            "where ct.teacher_id = :idTeacher", nativeQuery=true)
    List<ClassTeacherSubjectLink> findJournalsByTeacher(@Param("idTeacher") int idTeacher);

    /**
     * Find all active journals for a teacher by his id
     * @param idTeacher teacher's id
     * @return {@link ClassTeacherSubjectLink}
     */
    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n" +
            "where ct.teacher_id = :idTeacher and clazz.is_active=true ", nativeQuery=true)
    List<ClassTeacherSubjectLink> findActiveJournalsByTeacher(@Param("idTeacher") int idTeacher);

    /**
     * Find all journals f
     * @return {@link ClassTeacherSubjectLink}
     */
    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n", nativeQuery=true)
    List<ClassTeacherSubjectLink> findJournals();

    /**
     * Find active journal for a teacher by his id and subjectId
     * in class with transmitted id. Used to secure methods {@link SecurityExpressionService}
     * @param teacherId teacher's id
     * @param classId class's id
     * @param subjectId subject's id
     * @return {@link ClassTeacherSubjectLink} if found else null
     */
    @Query(value = "select * from class_teacher_subject_link ct\n" +
            "where teacher_id = :teacherId and clazz_id = :classId and subject_id = :subjectId and is_active = true", nativeQuery=true)
    ClassTeacherSubjectLink findByTeacherIdAndClazzIdAndSubjectId(@Param("teacherId") int teacherId,
                                                                  @Param("classId")int classId,
                                                                  @Param("subjectId")int subjectId);

    /**
     * Find active journal for a teacher by his id in class
     * with transmitted id.
     * @param teacherId teacher's id
     * @param classId class's id
     * @return ClassTeacherSubjectLink if found else null
     */
    @Query(value = "select * from class_teacher_subject_link ct\n" +
            "where teacher_id = :teacherId and clazz_id = :classId and is_active = true", nativeQuery=true)
    List<ClassTeacherSubjectLink> findByTeacherIdAndClazzId(@Param("teacherId") int teacherId,
                                                      @Param("classId")int classId);

    /**
     * Find all {@link ClassTeacherSubjectLink} by teacher id and subject id.
     * @param teacherId teacher's id
     * @param subjectId subject's id
     * @return list of {@link ClassTeacherSubjectLink} if found else null
     */
    List<ClassTeacherSubjectLink> findByTeacherIdAndSubjectId(int teacherId, int subjectId);

    /**
     * Find all {@link ClassTeacherSubjectLink} by class id.
     * @param classId teacher's id
     * @return list of {@link ClassTeacherSubjectLink} if found else null
     */
    List<ClassTeacherSubjectLink> findByClazzId(int classId);
}
