package academy.softserve.eschool.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.softserve.eschool.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	
	@Query(value="select date, lesson_number, name, hometask, mark, note" + 
			"	 from lesson" + 
			"    left join students_classes on lesson.clazz_id = students_classes.class_id" + 
			"    left join subject on lesson.subject_id = subject.id" + 
			"    left join mark on (lesson.id = mark.lesson_id and students_classes.student_id = mark.student_id)" + 
			"    where lesson.date between :startDate and :endDate" + 
			"    and students_classes.student_id = :studentId" + 
			"    order by lesson.date, lesson.lesson_number", nativeQuery=true)
	
	public List<Map<String, Object>> getDiary(@Param("studentId")int studentId, @Param("startDate")String startDate,
			@Param("endDate")String endDate);

	@Query(value = "select * from lesson where lesson.clazz_id=:idClass and lesson.subject_id=:idSubject\n" +
			"order by lesson.date", nativeQuery=true)
	List<Lesson> findHomework(@Param("idSubject")int idSubject, @Param("idClass")int idClass);
}
