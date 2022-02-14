package uz.pdp.lesson7.payload;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TimeTableDTO {
    //  frontenddan keladigan narsalar :
    private Integer teacher_id;
    private Integer group_id;
    private Integer school_id;
    private LocalTime startTime;
    private LocalTime endTime;
}
