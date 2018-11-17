package academy.softserve.eschool.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.model.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    
    /**
     * Returns list of Map objects. Each Map contains avg_mark, count and date
     * @param studentId if specified marks are filtered by user id
     * @param subjectId if specified marks are filtered by subject id
     * @param classId if specified marks are filtered by class id
     * @param startDate if specified only marks received after this date are returned
     * @param endDate if specified only marks received before this date are returned
     * @return list of Map objects
     */
    @Query(value="select AVG(m.mark) as avg_mark, COUNT(m.mark) as count, l.date as date "
            + "from mark m left join lesson l on m.lesson_id = l.id "
            + "where (:subjectId is null or l.subject_id = :subjectId)"
            + "and (:classId is null or l.clazz_id = :classId)"
            + "and (:studentId is null or m.student_id = :studentId)"
            + "and (:startDate is null or l.date >= :startDate)"
            + "and (:endDate is null or l.date <= :endDate) "
            + "group by l.date order by l.date", nativeQuery=true)
    List<Map<String, Object>> getFilteredByParamsGroupedByDate(@Param("subjectId") Integer subjectId, @Param("classId")Integer classId,
            @Param("studentId") Integer studentId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Modifying
    @Transactional
    @Query(value = "insert into mark(mark,note,lesson_id,student_id) values(:mark,:note,:idLesson,:idStudent)\n" +
            "on duplicate key update mark=values(mark),note = values(note)", nativeQuery = true)
    void saveMarkByLesson(@Param("idStudent") int idStudent,@Param("idLesson") int idLesson,
                                @Param("mark") byte mark,@Param("note") String note);

	@Query(value = "select  m.lesson_id, m.mark, l.date from mark m" +
			"inner join lesson l on l.id = m.lesson_id" +
            "where l.date >= curdate()", nativeQuery = true)
    List<Map<String, Object>> getMarksPuttedInTheFuture();

    @Modifying
    @Transactional
    @Query(value = "update lesson set mark_type=:markType where id=:idLesson", nativeQuery = true)
    void saveTypeByLesson(@Param("idLesson") int idStudent,@Param("markType") String markType);
}
