package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import static academy.softserve.eschool.model.Subject.SUBJECT_NAME_PATTERN;

@ApiModel
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SubjectDTO {

    @ApiModelProperty(required = true, notes = "Contains the ID of the subject.")
    private int subjectId;

    @ApiModelProperty(required = false, notes = "Contains the name of the subject: " +
            "the name must match the pattern " + SUBJECT_NAME_PATTERN + ", " +
            "so you should enter only Ukrainian characters." +
            "The name must have maximum length of 50 symbols. " +
            "For example, the subjects's name can be \"Фізика\", but not \"фізИка\" or \"Fisica\".")
    private String subjectName;

    @ApiModelProperty(required = false, notes = "Contains additional information describing the subject. Can be null. " +
            "It's maximum length is 500 characters.")
    private String subjectDescription;
}