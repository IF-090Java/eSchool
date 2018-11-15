package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
