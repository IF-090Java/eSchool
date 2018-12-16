package academy.softserve.eschool.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Table(name="mark")
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"student", "lesson"})
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Positive
    @Max(value=12)
    private byte mark;
    
    @Size(max=200)
    private String note;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    private Student student;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    private Lesson lesson;
    
    public Mark(byte mark, String note, Student student, Lesson lesson) {
        super();
        this.mark = mark;
        this.note = note;
        this.student = student;
        this.lesson = lesson;
    }
}
