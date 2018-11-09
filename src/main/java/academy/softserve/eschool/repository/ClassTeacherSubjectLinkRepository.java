package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import academy.softserve.eschool.model.ClassTeacherSubjectLinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTeacherSubjectLinkRepository extends JpaRepository<ClassTeacherSubjectLink, ClassTeacherSubjectLinkId> {

    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n" +
            "where ct.teacher_id = :idTeacher", nativeQuery=true)
    List<ClassTeacherSubjectLink> findJournalsByTeacher(@Param("idTeacher") int idTeacher);

    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n" +
            "where ct.teacher_id = :idTeacher and clazz.is_active=true ", nativeQuery=true)
    List<ClassTeacherSubjectLink> findActiveJournalsByTeacher(@Param("idTeacher") int idTeacher);

    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n", nativeQuery=true)
    List<ClassTeacherSubjectLink> findJournals();
/*
    @Query(value = "select * from class_teacher_subject_link\n" +
            "where teacher_id= :idTeacher AND subject_id= :idSubject AND clazz_id= :idClass\n", nativeQuery=true)
    ClassTeacherSubjectLink findByIds(@Param("idTeacher") int idTeacher, @Param("idClass") int idClass,
                                      @Param("idSubject") int idSubject);
 */
}
