package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Groups;
import uz.pdp.lesson7.entity.School;
import uz.pdp.lesson7.entity.Teacher;
import uz.pdp.lesson7.entity.TimeTable;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.TimeTableDTO;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.SchoolRrepository;
import uz.pdp.lesson7.repository.TeacherRrepository;
import uz.pdp.lesson7.repository.TimeTableRepository;

import java.util.Optional;

@Service
public class TimeTableService {
    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TeacherRrepository teacherRrepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SchoolRrepository schoolRrepository;

    public Responce all() {
        if (timeTableRepository.findAll().isEmpty()) {
            return new Responce("Nothing found", false);
        }
        return new Responce("All", true, timeTableRepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        return optionalTimeTable.map(timeTable -> new Responce("this", true, timeTable)).orElseGet(() -> new Responce("not found", false));
    }

    public Responce delete(Integer id) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isPresent()) {
            TimeTable timeTable = optionalTimeTable.get();
            timeTableRepository.delete(timeTable);
            return new Responce("Deleted", true, timeTable);
        }
        return new Responce("Not found", false);
    }

    public Responce add(TimeTableDTO dto) {
        // avvaldan borlari qo'shiladi


        Optional<Teacher> optionalTeacher = teacherRrepository.findById(dto.getTeacher_id());
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            if (groupRepository.findById(dto.getGroup_id()).isPresent()) {
                Groups group = groupRepository.getById(dto.getGroup_id());
                if (schoolRrepository.findById(dto.getSchool_id()).isPresent()) {
                    School school = schoolRrepository.getById(dto.getSchool_id());
                    TimeTable timeTable = new TimeTable();

                    timeTable.setEndTime(dto.getEndTime());
                    timeTable.setStartTime(dto.getStartTime());
                    // avval tekshirish kk shu gruppaga dars beradimi o'zi shu domla
                    if (teacher.getGroups().contains(group)) {
                        timeTable.setGroups(group);
                        timeTable.setSchool(school);
                        timeTable.setTeacher(teacher);

                        TimeTable save = timeTableRepository.save(timeTable);
                        return new Responce("Added", true, save);

                    }
                }
            }
        }
        return new Responce("Something went wrong", false);
    }

    public Responce edit(Integer id, TimeTableDTO dto) {
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isPresent()) {

            // bunda doim frondan to'gri malumot keladi deyilgandek go'yo

            TimeTable timeTable = optionalTimeTable.get();
            timeTable.setTeacher(teacherRrepository.getById(dto.getTeacher_id()));
            timeTable.setEndTime(dto.getEndTime());
            timeTable.setStartTime(dto.getStartTime());
            timeTable.setGroups(groupRepository.getById(dto.getGroup_id()));
            timeTable.setSchool(schoolRrepository.getById(dto.getSchool_id()));

            return new Responce("Edited", true, timeTable);
        }
        return new Responce("Something went wrong", false);
    }
}
