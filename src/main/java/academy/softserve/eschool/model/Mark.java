package academy.softserve.eschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="mark")
@Data
@EqualsAndHashCode(exclude = {"student", "lesson"})
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue
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
    
    public enum MarkType {
        Control, Practic, Module, Labaratorna
    }
}
