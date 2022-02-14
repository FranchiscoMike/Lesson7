package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Groups;
import uz.pdp.lesson7.entity.Student;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.StudentDTO;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;


    public Responce all() {
        if (studentRepository.findAll().isEmpty()) {
            return new Responce("Nothing found", true);
        }

        return new Responce("All", true, studentRepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.findAll().remove(student);

            return new Responce("One", true, student);

        }
        return new Responce("Nothing found", false);
    }

    public Responce delete(Integer id) {

        // student o'chayapti :
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.delete(student);
            return new Responce("Deleted", true, student);
        }
        return new Responce("Not found", false);
    }

    public Responce add(StudentDTO dto) {
        //checking student :
        for (Student student : studentRepository.findAll()) {
            if (student.getName().equals(dto.getStudent_name())) {
                return new Responce("Already exists", false);
            }
        }

        for (Groups groups : groupRepository.findAll()) {
            if (groups.getName().equals(dto.getGroup_name())) {
                // adding :
                Student student = new Student();
                student.setName(dto.getStudent_name());
                student.setGroup(groups);
                Student save = studentRepository.save(student);
                return new Responce("Added", true, save);
            }
        }
        return new Responce("Something went wrong", false);
    }

    public Responce edit(StudentDTO dto) {
        for (Student student : studentRepository.findAll()) {
            if (student.getName().equals(dto.getStudent_name())) {
                for (Groups groups : groupRepository.findAll()) {
                    if (groups.getName().equals(dto.getGroup_name())) {
                        student.setGroup(groups);
                        student.setName(dto.getStudent_name());
                        Student save = studentRepository.save(student);
                        return new Responce("Edited", true, save);
                    }
                }
            }
        }
        return new Responce("Something went wrong", false);
    }
}

