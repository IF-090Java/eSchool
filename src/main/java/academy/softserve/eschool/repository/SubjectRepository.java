package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
