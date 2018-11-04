package academy.softserve.eschool.controller;

import java.util.Date;
import java.util.List;

import academy.softserve.eschool.dto.ClassDTO;
import academy.softserve.eschool.dto.MarkDTO;
import academy.softserve.eschool.dto.MarkTypeDTO;
import academy.softserve.eschool.model.MarkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import academy.softserve.eschool.dto.MarkDataPointDTO;
import academy.softserve.eschool.service.base.MarkServiceBase;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/marks")
@Api(value = "Reads studets' marks")
public class MarksController {
	
	@Autowired
	private MarkServiceBase markService;
	
	@GetMapping("")
	@ApiOperation(value = "Get marks by date filtered by specified params")
	GeneralResponseWrapper<List<MarkDataPointDTO>> getMarks (
			@ApiParam(value = "filter results by student id", required = false) @RequestParam(value = "student_id", required = false) Integer studentId,
			@ApiParam(value = "filter results by subject id", required = false) @RequestParam(value = "subject_id", required = false) Integer subjectId,
			@ApiParam(value = "filter results by class id", required = false) @RequestParam(value = "class_id", required = false) Integer classId,
			@ApiParam(value = "get marks received after specified date", required = false) @RequestParam(value = "period_start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodStart,
			@ApiParam(value = "get marks received before specified date", required = false) @RequestParam(value = "period_end", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date periodEnd){
		
		GeneralResponseWrapper<List<MarkDataPointDTO>> response;
		List<MarkDataPointDTO> dataPoints = markService.getFilteredByParams(subjectId, classId, studentId, periodStart, periodEnd);
		response = new GeneralResponseWrapper<>(new Status(200, "OK"), dataPoints);
		return response;
	}

	@ApiOperation(value = "Save mark of students by lesson")
	@PostMapping
	public MarkDTO postMark(
		@ApiParam(value = "mark,note,lesson and student id", required = true)@RequestBody MarkDTO markDTO){
		markService.saveMark(markDTO);
		return markDTO;
	}

	@ApiOperation("Update mark's type of lesson")
	@PutMapping("/lessons/{idLesson}/marktype")
	public MarkTypeDTO editType(
			@ApiParam(value = "id of lesson", required = true) @PathVariable int idLesson,
			@ApiParam(value = "type of mark", required = true) @RequestBody MarkTypeDTO markType){
		markService.updateType(idLesson, markType.getMarkType());
		return markType;
	}
}
