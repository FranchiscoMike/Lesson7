package uz.pdp.lesson7.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class TimeTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @ManyToOne
    private Teacher teacher;

   @ManyToOne
    private Groups groups;

    @OneToOne
    private School school;

    private LocalTime startTime;

    private LocalTime endTime;
}
