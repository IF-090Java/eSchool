package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where login like :paltlogin", nativeQuery = true)
    List<User> findByPartOfLogin(@Param("paltlogin") String paltlogin);

    User findByLogin(String username);
}
