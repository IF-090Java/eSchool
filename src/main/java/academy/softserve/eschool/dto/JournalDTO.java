package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JournalDTO {
    @ApiModelProperty(notes = "id of subject")
    private int idSubject;
    @ApiModelProperty(notes = "id of class")
    private int idClass;
    @ApiModelProperty(notes = "name of subject")
    private String subjectName;
    @ApiModelProperty(notes = "class full name")
    private String className;
    @ApiModelProperty(notes = "year of journal")
    private int academicYear;
}
