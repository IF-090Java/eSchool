package com.catschool.ejournal.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @TableGenerator(name="employee_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")
    private int employeeId;

    @Getter @Setter @NonNull private String employeeName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "employee_department", joinColumns = @JoinColumn(name = "idemployee"), inverseJoinColumns = @JoinColumn(name = "iddepartment"))
    @Getter @Setter @NonNull private List<Department> departments;
}
