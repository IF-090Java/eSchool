package academy.softserve.eschool.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import academy.softserve.eschool.model.Lesson;

import javax.transaction.Transactional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

	/**
	 * Returns list of Map objects that contain data about one {@link DiaryEntryDTO}}.
	 * @param studentId student's id
	 * @param startDate first day of period
	 * @param endDate last day of period
	 * @return list of Map objects that contain data about one {@link DiaryEntryDTO}}.
	 */
	@Query(value = "select date, lesson_number, name, hometask, mark, note" +
			"	 from lesson" +
			"    left join students_classes on lesson.clazz_id = students_classes.class_id" +
			"    left join subject on lesson.subject_id = subject.id" +
			"    left join mark on (lesson.id = mark.lesson_id and students_classes.student_id = mark.student_id)" +
			"    where lesson.date between :startDate and :endDate" +
			"    and students_classes.student_id = :studentId" +
			"    order by lesson.date, lesson.lesson_number", nativeQuery = true)
	 List<Map<String, Object>> getDiary(@Param("studentId") int studentId, @Param("startDate") String startDate,
											  @Param("endDate") String endDate);

	@Query(value = "select * from lesson where lesson.clazz_id=:idClass and lesson.subject_id=:idSubject\n" +
			"order by lesson.date", nativeQuery = true)
	List<Lesson> findHomework(@Param("idSubject") int idSubject, @Param("idClass") int idClass);

	@Query(value = "select s.id, s.name, s.description" +
			"		from lesson l" +
			"		left join clazz c on l.clazz_id = c.id" +
			"		left join subject s on l.subject_id = s.id" +
			"		where YEARWEEK(l.date, 1) = YEARWEEK(CURDATE(), 1)" +
			"		and weekday(l.date)= :weekday and clazz_id = :classId and c.is_active = 1", nativeQuery = true)
	List<Map<String, Object>> scheduleByClassId(@Param("weekday") int weekday, @Param("classId") int class_id);

	@Modifying
	@Transactional
	@Query(value = "delete from lesson where lesson.date between :startDate and :endDate" +
			"		and clazz_id = :classId", nativeQuery = true)
	void deleteScheduleByBounds(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("classId") int class_id);

	@Query(value = "select * from lesson inner join file on lesson.homework_file_id = file.id where lesson.id=:idLesson " +
			"order by lesson.date", nativeQuery = true)
	Lesson findFile(@Param("idLesson") int idLesson);

	@Modifying
	@Transactional
	@Query(value = "update lesson set hometask=:hometask,homework_file_id=:idFile where id=:idLesson", nativeQuery = true)
	void saveHomeWork(@Param("hometask") String hometask, @Param("idFile") Integer idFile,  @Param("idLesson") Integer idLesson);
}