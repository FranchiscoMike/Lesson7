package uz.pdp.lesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uz.pdp.lesson7.entity.Student;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentDTO {
    // yangi o'quvchi ga yangi group ochilmaydi
   private String student_name;
   private String group_name;

}
