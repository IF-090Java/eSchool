package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from user u inner join student s on u.id = s.id inner join students_classes sc on s.id = sc.student_id " +
            "inner join clazz c on sc.class_id = c.id where c.id = :id group by u.id", nativeQuery = true)
    List<Student> findByClazzId(@Param("id") int id);
}
