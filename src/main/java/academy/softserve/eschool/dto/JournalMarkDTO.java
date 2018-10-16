package academy.softserve.eschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class JournalMarkDTO {
    @ApiModelProperty(notes = "name of student")
    private String name;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy/MM/dd")
    @ApiModelProperty(notes = "array of dates")
    private Date[] dates;
    @ApiModelProperty(notes = "array of marks")
    private int[] marks;
    @ApiModelProperty(notes = "array of marks types")
    private String[] mark_types;

    public JournalMarkDTO(String name, Date[] dates, int[] marks, String[] mark_types) {
        this.name=name;
        this.dates = dates;
        this.marks = marks;
        this.mark_types = mark_types;
    }
}
