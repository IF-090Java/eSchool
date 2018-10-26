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

    @Query(value = "SELECT * FROM clazz WHERE is_active=:isActive", nativeQuery=true)
    List<Clazz> findClassByActiveStatus(@Param("isActive") boolean value);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clazz SET name=:className, academic_year=:classYear, description=:classDesc, is_active=:isActive WHERE id=:id", nativeQuery=true)
    void updateClass(@Param("id") int id, @Param("className") String name,
                     @Param("classYear") int year, @Param("classDesc") String desc,
                     @Param("isActive") boolean isActive);

    @Query(value = "select c.* from clazz c "
            + "inner join students_classes s "
            + "on c.id = s.class_id where c.is_active=true "
            + "group by s.class_id", nativeQuery = true)
    List<Clazz> getActiveClassesWithStudents();
}
