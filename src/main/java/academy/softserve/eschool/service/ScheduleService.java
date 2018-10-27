package academy.softserve.eschool.service;

import academy.softserve.eschool.dto.ScheduleDTO;

public interface ScheduleService {

    ScheduleDTO getScheduleByClassId(int id_class);
}
