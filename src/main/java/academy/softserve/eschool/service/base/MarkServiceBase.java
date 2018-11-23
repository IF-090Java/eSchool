package academy.softserve.eschool.service.base;

import java.time.LocalDate;
import java.util.List;

import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.dto.MarkDescriptionDTO;

public interface MarkServiceBase {
    List<MarkDataPointDTO> getFilteredByParams(Integer subjectId, Integer classId, Integer studentId, LocalDate startDate, LocalDate endDate);
    MarkDTO saveMark(MarkDTO dto);
    void updateType(int idLesson, String markType);
}
