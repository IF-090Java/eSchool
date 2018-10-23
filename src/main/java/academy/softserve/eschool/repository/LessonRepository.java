package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
