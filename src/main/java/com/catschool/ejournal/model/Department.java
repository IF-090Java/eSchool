package com.catschool.ejournal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@NoArgsConstructor
@RequiredArgsConstructor
public class Department {
    @TableGenerator(name="department_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "department_gen")
    private int departmentId;

    @Getter @Setter @NonNull private String departmentName;

    @JsonIgnore
    @ManyToMany(mappedBy = "departments", cascade = CascadeType.PERSIST)
    @Getter @Setter @NonNull private List<Employee> employees;
}
