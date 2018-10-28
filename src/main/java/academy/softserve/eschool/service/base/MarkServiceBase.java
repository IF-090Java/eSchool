package academy.softserve.eschool.service.base;

import java.util.Date;
import java.util.List;

import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkDataPointDTO;

public interface MarkServiceBase {
	public List<MarkDataPointDTO> getFilteredByParams(Integer subjectId, Integer classId, Integer studentId, Date startDate, Date endDate);
	void saveMark(MarkDTO dto);
}
