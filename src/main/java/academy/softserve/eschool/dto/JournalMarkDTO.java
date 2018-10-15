package academy.softserve.eschool.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JournalMarkDTO {
    private String name;
    private Date[] dates;
    private int[] marks;
    private String[] mark_types;

    public JournalMarkDTO(String name, Date[] dates, int[] marks, String[] mark_types) {
        this.name=name;
        this.dates = dates;
        this.marks = marks;
        this.mark_types = mark_types;
    }
}
