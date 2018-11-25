package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {
    @ApiModelProperty(notes = "Contains the ID of the mark (it's used only when the controller returns an object " +
            "to browser in POST method). It's a generated value in the database. It can't be null.")
    private int idMark;
    
    @ApiModelProperty(required = true, notes = "Contains the student's ID.")
    private int idStudent;
    
    @ApiModelProperty(required = true, notes = "Contains the lesson's ID.")
    private int idLesson;

    @ApiModelProperty(notes = "Contains the mark received by the pupil. It should be a positive number (greater than 0) " +
            "and it's maximum value is 12.")
    private Byte mark;

    @ApiModelProperty(notes = "Contains the note (additional description of the mark). It's maximum length is 200 characters.")
    private String note;
}
