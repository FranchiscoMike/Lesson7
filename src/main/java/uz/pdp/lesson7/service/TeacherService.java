package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Groups;
import uz.pdp.lesson7.entity.Subject;
import uz.pdp.lesson7.entity.Teacher;
import uz.pdp.lesson7.payload.TeacherDTO;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.SubjectRrepository;
import uz.pdp.lesson7.repository.TeacherRrepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRrepository teacherRrepository;

    @Autowired
    SubjectRrepository subjectRrepository;

    @Autowired
    GroupRepository groupRepository;
    // bularni hammasi admin page uchun Bo'ladi !!!

    public Responce all() {
        if (teacherRrepository.findAll().isEmpty()) {
            return new Responce("Nothing found", true);
        }
        return new Responce("All", true, teacherRrepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRrepository.findById(id);
        return optionalTeacher.map(teacher -> new Responce("Found", true, teacher)).orElseGet(() -> new Responce("not found", false));
    }

    public Responce delete(Integer id) {
        Optional<Teacher> optionalTeacher = teacherRrepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacherRrepository.delete(teacher);

            //  teeacher o'chgandan keyin unga tegishli malumotlar ham ochadi!


            return new Responce("Deleted", true, teacher);
        }
        return new Responce("Not found", false);
    }

    public Responce add(TeacherDTO dto) {
        String teacher_name = dto.getTeacher_name();
        List<Groups> groups = dto.getGroups();
        Subject subject = dto.getSubject();

        // maktabga yangi o'qituchi kelganda uni yangi o'qitadigan
        // fani bo'ladi va u uchun
        // alohida gruppa ham ochilishi mumkin
        Optional<Teacher> optionalTeacher = teacherRrepository.findByName(teacher_name);

        if (optionalTeacher.isPresent()) {
            return new Responce("Teacher already exists", false);

        }

        // subject cheking  is not necessary
        subjectRrepository.save(subject);
        groupRepository.saveAll(groups);

        Teacher save = teacherRrepository.save(new Teacher(teacher_name, subject, groups));
        return new Responce("Added", true, save);


    }

    public Responce edit(Integer id, TeacherDTO dto) {

        Optional<Teacher> optionalTeacher = teacherRrepository.findById(id);
        if (optionalTeacher.isPresent()) {

            List<Groups> groups = dto.getGroups();
            Subject subject = dto.getSubject();

            groupRepository.saveAll(groups);
            subjectRrepository.save(subject);


            Teacher teacher = optionalTeacher.get();
            teacher.setGroups(dto.getGroups());
            teacher.setName(dto.getTeacher_name());
            teacher.setProfessional_subject(dto.getSubject());

            Teacher save = teacherRrepository.save(teacher);
            return new Responce("Edited", true, save);
        }
        return new Responce("Something went wrong", false);
    }
}
