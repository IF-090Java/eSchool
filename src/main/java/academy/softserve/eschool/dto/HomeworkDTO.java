package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeworkDTO{
    @ApiModelProperty(required = true,
            notes = "Contains the ID of the lesson. It's a generated value in the database.")
    private int idLesson;
    
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy.MM.dd",
            timezone="Europe/Kiev")
    @ApiModelProperty(notes = "Contains the date of the homework in format: \"yyyy.MM.dd\". ")
    private Date date;
    
    @ApiModelProperty(notes = "Contains the homework. It's maximum length is 500 characters.")
    private String homework;
  
    @ApiModelProperty(notes = "Contains the homework file.")
    private String fileName;
}
