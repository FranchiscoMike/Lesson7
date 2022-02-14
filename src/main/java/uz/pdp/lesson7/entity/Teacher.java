package uz.pdp.lesson7.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Teacher {
    public Teacher(String name, Subject professional_subject, List<Groups> groups) {
        this.name = name;
        this.professional_subject = professional_subject;
        this.groups = groups;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne // 1 ta fandan ko'p o'qituvchi dars beradi
    private Subject professional_subject;

    @ManyToMany
    private List<Groups> groups;
}
