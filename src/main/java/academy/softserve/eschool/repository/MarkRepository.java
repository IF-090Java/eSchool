package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
