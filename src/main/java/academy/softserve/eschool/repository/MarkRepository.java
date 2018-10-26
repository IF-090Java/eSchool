package academy.softserve.eschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.softserve.eschool.model.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
	
	@Query(value="select AVG(m.mark), l.date "
			+ "from mark m left join lesson l on m.lesson_id = l.id "
			+ "where (:subjectId is null or l.subject_id = :subjectId)"
			+ "and (:classId is null or l.clazz_id = :classId)"
			+ "and (:studentId is null or m.student_id = :studentId)"
			+ "and (:startDate is null or l.date >= :startDate)"
			+ "and (:endDate is null or l.date <= :endDate) "
			+ "group by l.date order by l.date", nativeQuery=true)
	public List<Object[]> getFilteredByParamsGroupedByDate(@Param("subjectId") Integer subjectId, @Param("classId")Integer classId,
			@Param("studentId") Integer studentId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	
}
