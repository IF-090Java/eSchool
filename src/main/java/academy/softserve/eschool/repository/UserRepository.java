package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
