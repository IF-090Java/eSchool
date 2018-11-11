package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find all users, who have transmitted login as part of their own login.
     * @param login login
     * @return list of found users.
     */
    @Query(value = "select * from user where login like :partlogin", nativeQuery = true)
    List<User> findByPartOfLogin(@Param("partlogin") String login);

    User findByLogin(String username);
}
