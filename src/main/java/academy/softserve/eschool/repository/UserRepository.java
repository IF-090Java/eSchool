package academy.softserve.eschool.repository;

import academy.softserve.eschool.dto.AddedUsersDTO;
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

    @Query("SELECT u FROM User u WHERE u.login LIKE CONCAT(:partLogin,'%') ORDER BY u.login")
    List<User> findUsersWithPartOfLogin(@Param("partLogin") String login);

    User findByLogin(String username);
    
    User findByEmail(String email);
    
    User findByLoginOrEmail(String login, String email);

    @Query("select " +
    "new academy.softserve.eschool.dto.AddedUsersDTO(u.firstName, u.lastName, u.patronymic, u.role, u.login, u.password) " +
    "from User u where u.role NOT LIKE '%ADMIN%' ORDER BY u.id desc ")
    List<AddedUsersDTO> getRegisteredUsers();
}
