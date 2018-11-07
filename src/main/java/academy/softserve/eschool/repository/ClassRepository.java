package academy.softserve.eschool.repository;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Clazz, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update clazz set is_active=:status where id=:classId", nativeQuery = true)
    void updateClassStatusById(@Param("classId") int classId, @Param("status") boolean status);

}
