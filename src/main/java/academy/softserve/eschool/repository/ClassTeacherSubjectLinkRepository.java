package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.ClassTeacherSubjectLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTeacherSubjectLinkRepository extends JpaRepository<ClassTeacherSubjectLink, Integer> {

    @Query(value = "select distinct * from class_teacher_subject_link ct\n" +
            "left join clazz on ct.clazz_id=clazz.id\n" +
            "left join subject on subject.id=ct.subject_id\n" +
            "left join teacher on teacher.id=ct.teacher_id\n" +
            "where ct.teacher_id = :idTeacher", nativeQuery=true)
    List<ClassTeacherSubjectLink> findJournalsByTeacher(@Param("idTeacher") int idTeacher);
}
