package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkDescriptionDTO {
    @ApiModelProperty(notes = "Contains the ID of the lesson. It's a generated value in the database.")
    private int idLesson;

    @ApiModelProperty(notes = "Contains the mark received by the pupil. It should be a positive number (greater than 0) " +
            "and it's maximum value is 12.")
    private Byte mark;
    
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd",
            timezone="Europe/Kiev")
    @ApiModelProperty(notes = "Contains the date when the mark was puttedin format \"yyyy.MM.dd\".")
    private Date dateMark;
    
    @ApiModelProperty(notes = "Contains the type of the mark. " +
            "It can be one of the following: Control, Practic, Module or Labaratorna.")
    private String typeMark;
    
    @ApiModelProperty(notes = "Contains the note (additional description of the mark). It's maximum length is 200 characters.")
    private String note;

}
