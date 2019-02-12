package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from user u inner join student s on u.id = s.id inner join students_classes sc on s.id = sc.student_id " +
            "inner join clazz c on sc.class_id = c.id where c.id = :id group by u.id", nativeQuery = true)
    List<Student> findByClazzId(@Param("id") int id);


    @Query(value="select student.id as id_student ,user.first_name,user.last_name,lesson.id,mark.mark,lesson.date,mark_type.mark_type,mark.note from student\n" +
            "left join user on student.id=user.id\n" +
            "left join students_classes on students_classes.student_id=student.id\n" +
            "right join lesson on students_classes.class_id=lesson.clazz_id\n" +
            "left join mark on (lesson.id=mark.lesson_id and mark.student_id = student.id) left join mark_type on lesson.mark_type_id=mark_type.id\n" +
            "where lesson.clazz_id=:idClass and lesson.subject_id=:idSubject and user.enabled = true\n" +
            "order by student.id,lesson.id", nativeQuery=true)
    List<Map<String,Object>> findJournal(@Param("idSubject")int idSubject, @Param("idClass")int idClass);
}