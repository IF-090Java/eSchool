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
    private final static String CLASS_NAME_PATTERN = "\\d{1,2}-?[А-ЯІЇЄҐа-яіїєґ]?";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank
    @Size(max=4)
    @RegexPattern(pattern=CLASS_NAME_PATTERN, message="Input must match " + CLASS_NAME_PATTERN)
    private String name;
    
    @Size(max=500)
    private String description;
    
    @NotNull
    @Min(value=2000)
    @Column(name="academic_year")
    private int academicYear;
    
    @NotNull
    @Column(name="is_active")
    private boolean isActive;
    
    @ManyToMany(mappedBy = "classes", 
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final Set<@NotNull Student> students = new HashSet<>();
    
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
    private final Set<@NotNull ClassTeacherSubjectLink> CTSlinks = new HashSet<>();
    
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "clazz")
    private final Set<@NotNull Lesson> schedule = new HashSet<>();

    //todo bk ++ remove all unused constructors.
}
