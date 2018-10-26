package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from user u inner join student s on u.id = s.id inner join students_classes sc on s.id = sc.student_id " +
            "inner join clazz c on sc.class_id = c.id where c.id = :id group by u.id", nativeQuery = true)
    List<Student> findByClazzId(@Param("id") int id);

    /*@Query(value="select * from student\n" +
            "left join user on student.id=user.id\n" +
            "left join students_classes on students_classes.student_id=student.id\n" +
            "right join lesson on students_classes.class_id=lesson.clazz_id\n" +
            "left join mark on (lesson.id=mark.lesson_id and mark.student_id = student.id)\n" +
            "where lesson.clazz_id=3 and lesson.subject_id=1\n", nativeQuery=true)
    Set<Student> findJournal(@Param("idSubject")int idSubject, @Param("idClass")int idClass);*/

    @Query(value="select user.first_name,user.last_name,mark.mark,lesson.date,lesson.mark_type from student\n" +
            "left join user on student.id=user.id\n" +
            "left join students_classes on students_classes.student_id=student.id\n" +
            "right join lesson on students_classes.class_id=lesson.clazz_id\n" +
            "left join mark on (lesson.id=mark.lesson_id and mark.student_id = student.id)\n" +
            "where lesson.clazz_id=:idClass and lesson.subject_id=:idSubject\n" +
            "order by student.id,lesson.date", nativeQuery=true)
    List<Object[]> findJournal(@Param("idSubject")int idSubject, @Param("idClass")int idClass);
}