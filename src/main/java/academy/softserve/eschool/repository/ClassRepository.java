package academy.softserve.eschool.repository;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.model.Clazz;
import academy.softserve.eschool.model.Subject;

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
    
    /**
     * Finds classes that study specified subject
     * @param subjectId id of specified subject
     * @return list of Clazz objects
     */
    @Query(value = "Select distinct clazz.id, clazz.name, clazz.description, clazz.academic_year, clazz.is_active " +
    		" from class_teacher_subject_link left join clazz " +
            " on class_teacher_subject_link.clazz_id=clazz.id" +
            " where subject_id=:subjectId", nativeQuery=true)
	List<Clazz> findClassesBySubject(Integer subjectId);

}
