package academy.softserve.eschool.service.base;

import java.time.LocalDate;
import java.util.List;

import academy.softserve.eschool.dto.DiaryEntryDTO;

public interface DiaryServiceBase {
    List<DiaryEntryDTO> getDiary(LocalDate weekStart, int studentId);
}
