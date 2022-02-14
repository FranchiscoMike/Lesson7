package uz.pdp.lesson7.payload;

import lombok.*;
import uz.pdp.lesson7.entity.Groups;
import uz.pdp.lesson7.entity.Subject;

import javax.swing.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherDTO {
    String teacher_name;
    Subject subject;
    List<Groups> groups;
}
