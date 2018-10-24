package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Clazz, Integer> {
}
