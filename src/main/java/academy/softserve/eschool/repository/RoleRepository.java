package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
