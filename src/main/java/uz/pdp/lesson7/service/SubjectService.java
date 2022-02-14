package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Subject;
import uz.pdp.lesson7.entity.Teacher;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.SubjectRrepository;
import uz.pdp.lesson7.repository.TeacherRrepository;

import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRrepository subjectRrepository;

    @Autowired
    TeacherRrepository teacherRrepository;

    public Responce all() {
        if (subjectRrepository.findAll().isEmpty()) {
            return new Responce("Not found", false);
        }
        return new Responce("All", true, subjectRrepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<Subject> optionalSubject = subjectRrepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            return new Responce("Found", true, subject);
        }
        return new Responce("Not found", false);
    }

    public Responce delete(Integer id) {
        Optional<Subject> optionalSubject = subjectRrepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subjectRrepository.delete(subject);

            // shu fandan dars beradigan o'qituvchilar hamo o'chadi :
            for (Teacher teacher : teacherRrepository.findAll()) {
                if (teacher.getProfessional_subject().equals(subject.getName())){
                    teacherRrepository.delete(teacher);
                    break;
                }
            }

            return new Responce("deleted", true, subject);
        }
        return new Responce("Not found", false);
    }

    public Responce edit(Integer id, String new_subject_name) {
        Optional<Subject> optionalSubject = subjectRrepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setName(new_subject_name);
            Subject save = subjectRrepository.save(subject);
            return new Responce("Edited", true, save);
        }
        return new Responce("Not found", false);
    }


    public Responce add(String new_subject_name) {
        Optional<Subject> byName = subjectRrepository.findByName(new_subject_name);
        if (byName.isPresent()) {
            return new Responce("This is already exists", false);
        }
        Subject subject = new Subject(new_subject_name);
        Subject save = subjectRrepository.save(subject);

        return new Responce("Added", true, save);
    }
}
