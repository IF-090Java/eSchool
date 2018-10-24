package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT * FROM student WHERE id=:idUser", nativeQuery=true)
    public Student findStudentById(@Param("idUser") int id);
}
