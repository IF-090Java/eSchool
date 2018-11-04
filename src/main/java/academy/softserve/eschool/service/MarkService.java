package academy.softserve.eschool.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.model.MarkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.repository.MarkRepository;
import academy.softserve.eschool.service.base.MarkServiceBase;

@Service
public class MarkService implements MarkServiceBase{

	@Autowired
	private MarkRepository markRepo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public List<MarkDataPointDTO> getFilteredByParams(Integer subjectId, Integer classId, Integer studentId, Date periodStart,
			Date periodEnd) {
		
		String startDate = null;
		String endDate = null;
		
		if (periodStart != null) {
			startDate = dateFormat.format(periodStart);
		}
		if (periodEnd != null) {
			endDate = dateFormat.format(periodEnd);
		}
		List<Map<String, Object>> marks = markRepo.getFilteredByParamsGroupedByDate(subjectId, classId, studentId, startDate, endDate);
		List<MarkDataPointDTO> dataPoints = formDataPoints(marks);
		return dataPoints;
	}
	

	private List<MarkDataPointDTO> formDataPoints(List<Map<String, Object>> data) {
		List<MarkDataPointDTO> dataPoints;
		dataPoints = data.stream().map((obj) -> {
				double averageMark = ((BigDecimal)obj.get("avg_mark")).doubleValue();
				Date date = (Date)obj.get("date");
				int count = ((BigInteger)obj.get("count")).intValue();
				return new MarkDataPointDTO(averageMark, date, count);
			})
			.collect(Collectors.toList());
		System.out.println(dataPoints.toString());
		return dataPoints;
	}

	@Override
	public void saveMark(MarkDTO dto) {
		markRepo.saveMarkByLesson(dto.getIdStudent(),dto.getIdLesson(),dto.getMark(),dto.getNote());
	}

	@Override
	public void updateType(int idLesson, String markType) {
		markRepo.saveTypeByLesson(idLesson,markType);
	}
}
