package academy.softserve.eschool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class JournalMarkDTO {
    @ApiModelProperty(notes = "name of student")
    private String name;
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
