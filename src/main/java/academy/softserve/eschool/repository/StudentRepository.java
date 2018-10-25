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

    @Query(value="select * from student\n" +
            "left join user on student.id=user.id\n" +
            "left join students_classes on students_classes.student_id=student.id\n" +
            "right join lesson on students_classes.class_id=lesson.clazz_id\n" +
            "left join mark on (lesson.id=mark.lesson_id and mark.student_id = students_classes.student_id)\n" +
            "where lesson.clazz_id=3 and lesson.subject_id=1\n", nativeQuery=true)
    List<Student> findJournal(@Param("idSubject")int idSubject, @Param("idClass")int idClass);
}
