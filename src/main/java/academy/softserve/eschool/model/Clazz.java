package academy.softserve.eschool.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import academy.softserve.eschool.constraint.annotation.RegexPattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="clazz")
@EqualsAndHashCode(exclude={"students", "CTSlinks", "schedule"})
@ToString(of = {"id","name"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clazz {    
    public final static String CLASS_NAME_PATTERN = "[\\dА-ЯІЇЄҐа-яіїєґ()' -]+";
    /**
    * Id of the class.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /**
    * Name of the class.
    */
    @NotBlank
    @Size(max=20)
    @RegexPattern(pattern=CLASS_NAME_PATTERN, message="Input must match " + CLASS_NAME_PATTERN)
    private String name;

    /**
    * Description of the class.
    */
    @Size(max=500)
    private String description;

    /**
    * Academic year of the class. Like 2018, 2019 etc.
    */
    @NotNull
    @Min(value=2000)
    @Column(name="academic_year")
    private int academicYear;

    /**
    * Is Active attribute of the class. Class must be active in currently year
    * and inactive in next years.
    */
    @NotNull
    @Column(name="is_active")
    private boolean isActive;

    /**
    * List of {@link Student} objects. One class may contains many students.
    */
    @ManyToMany(mappedBy = "classes", 
        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<@NotNull Student> students = new HashSet<>();

    /**
    * List of {@link ClassTeacherSubjectLink} objects. One class may contains many Class-Teacher-Subject pairs.
    */
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
    private final Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();

    /**
    * List of {@link Lesson} objects. One class has many lessons.
    */
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
    private final Set<@NotNull Lesson> schedule = new HashSet<>();
    //todo bk ++ remove all unused constructors.
}
