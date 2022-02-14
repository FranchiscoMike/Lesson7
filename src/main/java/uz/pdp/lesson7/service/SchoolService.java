package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.School;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.SchoolDTO;
import uz.pdp.lesson7.repository.SchoolRrepository;

import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    SchoolRrepository schoolRrepository;

    public Responce edit(Integer id, SchoolDTO dto) {
        boolean isEdited = false;
        Optional<School> optionalSchool = schoolRrepository.findById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setName(dto.getName());
            isEdited = true;
            return new Responce("Edited", isEdited, school);
        }
        return new Responce("Something went wrong!", false);
    }


    public Responce add(SchoolDTO dto) {
        boolean isValid = true;
        for (School school : schoolRrepository.findAll()) {
            if (school.getName().equals(dto.getName())) {
                isValid = false;
            }
        }
        if (!isValid) {
            School school = new School();
            school.setName(dto.getName());
            School save = schoolRrepository.save(school);
            return new Responce("Added", true, save);
        }
        return new Responce("Something went wrong", false);
    }


    public Responce delete(Integer id) {

        Optional<School> optionalSchool = schoolRrepository.findById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            schoolRrepository.delete(school);
            return new Responce("Deleted", true, school);
        }
        return new Responce("Deleted", false);
    }
}
