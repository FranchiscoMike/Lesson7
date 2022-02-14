package uz.pdp.lesson7.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupDTO {
   private String new_group_name;
   private String school_name;
}
