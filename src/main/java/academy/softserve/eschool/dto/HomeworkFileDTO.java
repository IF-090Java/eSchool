package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeworkFileDTO{
    @ApiModelProperty(required = true, notes="Contains the ID of the lesson. It's a generated value in the database.")
    private int idLesson;
    @ApiModelProperty(notes = "Contains the homework's description. It's maximum length is 500 characters.")
    private String homework;
    @ApiModelProperty(notes = "Contains Base64 representation of the file(use with fileName and fileType). " +
            "It's maximum size is 500KB.")
    private String fileData;
    @ApiModelProperty(notes = "Contains the name of the file(use with fileData and fileType). It can't be null.")
    private String fileName;
    @ApiModelProperty(notes = "Contains the mime type of file(use with fileData and fileName).")
    private String fileType;

}
