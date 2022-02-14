package uz.pdp.lesson7.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne // 1ta maktabda ko'p group bo'ladi
    private School school;

    public Groups(String name, School school) {
        this.name = name;
        this.school = school;
    }
}
