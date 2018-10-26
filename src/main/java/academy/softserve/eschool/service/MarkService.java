package academy.softserve.eschool.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
		List<Object[]> marks = markRepo.getFilteredByParamsGroupedByDate(subjectId, classId, studentId, startDate, endDate);
		List<MarkDataPointDTO> dataPoints = formDataPoints(marks);
		return dataPoints;
	}
	

	private List<MarkDataPointDTO> formDataPoints(List<Object[]> data) {
		List<MarkDataPointDTO> dataPoints;
		dataPoints = data.stream().map((obj) -> {
				double averageMark = ((BigDecimal)obj[0]).doubleValue();
				Date date = (Date)obj[1];
				return new MarkDataPointDTO(averageMark, date);
			})
			.collect(Collectors.toList());
		System.out.println(dataPoints.toString());
		return dataPoints;
	}

}
