package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SubjectDTO {

	@ApiModelProperty(required = true, notes = "Id of the subject")
	private int subjectId;

	@ApiModelProperty(required = false, notes = "Subject name. For example: history. Can be null")
	private String subjectName;

	@ApiModelProperty(required = false, notes = "Additional information describing the subject. Can be null")
	private String subjectDescription;
}