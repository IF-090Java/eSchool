package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexRepository extends JpaRepository<Sex, Integer> {
}
