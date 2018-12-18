package academy.softserve.eschool.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import academy.softserve.eschool.dto.SubjectAvgMarkDTO;
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
    @Query(value="select AVG(CAST(m.mark as DECIMAL)) as avg_mark, COUNT(m.mark) as count, l.date as date "
            + "from mark m left join lesson l on m.lesson_id = l.id "
            + "where (:subjectId is null or l.subject_id = :subjectId)"
            + "and (:classId is null or l.clazz_id = :classId)"
            + "and (:studentId is null or m.student_id = :studentId)"
            + "and (:startDate is null or l.date >= :startDate)"
            + "and (:endDate is null or l.date <= :endDate) "
            + "group by l.date order by l.date", nativeQuery=true)
    List<Map<String, Object>> getFilteredByParamsGroupedByDate(@Param("subjectId") Integer subjectId, @Param("classId")Integer classId,
            @Param("studentId") Integer studentId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * This method save student's mark by lesson
     * @param idStudent  id of student
     * @param idLesson  id of lesson
     * @param mark  mark value
     * @param note  note for mark or lesson
     */
    @Modifying
    @Transactional
    @Query(value = "insert into mark(mark,note,lesson_id,student_id) values(:mark,:note,:idLesson,:idStudent)\n" +
            "on duplicate key update mark=values(mark),note = values(note)", nativeQuery = true)
    void saveMarkByLesson(@Param("idStudent") int idStudent,@Param("idLesson") int idLesson,
                                @Param("mark") byte mark,@Param("note") String note);


    Mark findTopByStudentIdAndLessonId(int studentId,int lessonId);
    /**
     * This method save mark's type of lesson
     * @param idLesson  id of lesson
     * @param markTypeID  mark type id.
     */
    @Modifying
    @Transactional
    @Query(value = "update lesson set mark_type_id = :markTypeID where id=:idLesson", nativeQuery = true)
    void saveTypeByLesson(@Param("idLesson") int idLesson,@Param("markTypeID") int markTypeID);

    
    /**
     * Returns average marks grouped by subject for specified student
     * @param studentId id of student
     * @return list of {@link SubjectAvgMarkDTO}
     */
    @Query("select new academy.softserve.eschool.dto.SubjectAvgMarkDTO(avg(m.mark), m.lesson.subject.id, m.lesson.subject.name)"
            + " from Mark m"
            + " where m.student.id = :studentId"
            + " and (m.lesson.date >= :startDate or :startDate is null)"
            + " and (m.lesson.date <= :endDate  or :endDate is null)"
            + " group by m.lesson.subject.id")
    List<SubjectAvgMarkDTO> getFilteredByStudentGroupedBySubject(
            @Param("studentId") Integer studentId, 
            @Param("startDate") Date startDate, 
            @Param("endDate") Date endDate);
}
