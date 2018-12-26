package academy.softserve.eschool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the lesson table of the DB
 *
 * @author Mariana Vorotniak
 */
@Entity
@Table(name = "lesson")
@Data
@EqualsAndHashCode(exclude = {"marks", "subject", "clazz", "file"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    /**
     * Id of the lesson.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Number of the lesson in the schedule.
     */
    @NotNull
    @Positive
    @Column(name = "lesson_number")
    private byte lessonNumber;
    /**
     * Date of the lesson. It must be initialized with a date in the future.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd", timezone="Europe/Kiev")
    private Date date;
    /**
     * Homework that the teacher gives for the pupils.
     */
    @Size(max = 500)
    private String hometask;
    /**
     * Type of mark putted to the pupil for the lesson.
     * The ENUM with mark types is specified as an internal class in the {@link Mark} class: {@link MarkType}
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "mark_type_id")
    private MarkType markType;
    /**
     * File object {@link File} that the teacher uploads for the pupils, as an addition to the lesson
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homework_file_id")
    private File file;
    /**
     * {@link Clazz} object, which id we use in the DB
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    private Clazz clazz;
    /**
     * {@link Subject} object, which id we use in the DB
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @NotNull
    private Subject subject;
    /**
     * List of {@link Mark} objects. There can be many marks for one lesson.
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "lesson")
    private final Set<@NotNull Mark> marks = new HashSet<>();

    public Lesson(byte lessonNumber, Date date, String hometask, MarkType markType, File file, Clazz clazz,
            Subject subject) {
        super();
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.hometask = hometask;
        this.markType = markType;
        this.file = file;
        this.clazz = clazz;
        this.subject = subject;
    }
}
