package academy.softserve.eschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * This class represents the class_teacher_subject_link table of the DB
 *
 * @author Mariana Vorotniak
 */
@Entity
@IdClass(ClassTeacherSubjectLinkId.class)
@Table(name="class_teacher_subject_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"clazz_id", "teacher_id", "subject_id"})
@Builder
public class ClassTeacherSubjectLink{
    /**
     * Id of the class.
     */
    @Id
    @Column(name = "clazz_id")
    @NotNull
    private int clazz_id;
    /**
     * Id of the teacher.
     */
    @Id
    @Column(name = "teacher_id")
    @NotNull
    private int teacher_id;
    /**
     * Id of the subject.
     */
    @Id
    @Column(name = "subject_id")
    @NotNull
    private int subject_id;
    /**
     * {@link Clazz} object, which id we use in the DB
     */
    @Id
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="clazz_id", insertable = false, updatable = false)
    private Clazz clazz;
    /**
     * {@link Teacher} object, which id we use in the DB
     */
    @Id
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teacher teacher;
    /**
     * {@link Subject} object, which id we use in the DB
     */
    @Id
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;
    /**
     * Is Active attribute of the class. It indicates if the connection
     * between the teacher, class and subject is up-to-date
     */
    @NotNull
    @Column(name="is_active")
    private boolean isActive;
    
    public ClassTeacherSubjectLink(Clazz clazz, Teacher teacher, Subject subject, boolean isActive) {
        super();
        this.clazz = clazz;
        this.teacher = teacher;
        this.subject = subject;
        this.isActive = isActive;
    }
}
