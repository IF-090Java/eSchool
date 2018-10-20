package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
	@ApiModelProperty(required = true, notes = "Id of the subject")
	private int id_subject;

	@ApiModelProperty(required = false, notes = "Subject name. For example: history. Can be null")
	private String subjectName;
}