package academy.softserve.eschool.service.base;

import java.util.Date;
import java.util.List;

import academy.softserve.eschool.dto.DiaryEntryDTO;

public interface DiaryServiceBase {
	List<DiaryEntryDTO> getDiary(Date weekStart, int studentId);
}
