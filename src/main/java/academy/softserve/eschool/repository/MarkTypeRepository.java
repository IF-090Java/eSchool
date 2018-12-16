package academy.softserve.eschool.repository;

import academy.softserve.eschool.model.MarkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarkTypeRepository extends JpaRepository<MarkType, Integer> {

    @Query("select m_t from MarkType m_t where m_t.markType = :mark_type")
    MarkType findByMarkType(@Param("mark_type") String markType);
}
