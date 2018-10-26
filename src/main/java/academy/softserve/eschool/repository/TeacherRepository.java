package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
