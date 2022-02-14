package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Groups;
import uz.pdp.lesson7.entity.School;
import uz.pdp.lesson7.entity.Teacher;
import uz.pdp.lesson7.payload.GroupDTO;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.SchoolDTO;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.SchoolRrepository;
import uz.pdp.lesson7.repository.TeacherRrepository;

import java.util.Optional;

import static java.util.Optional.*;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeacherRrepository teacherRrepository;

    @Autowired // for checking schools!
    SchoolRrepository schoolRrepository;

    @Autowired
    SchoolService schoolService;

    public Responce all() {
        // avval checking qilish kerak !
        if (groupRepository.findAll().isEmpty()) {
            return new Responce("Store is empty", false);
        }
        return new Responce("All", true, groupRepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<Groups> optional = groupRepository.findById(id);
        return optional.map(groups -> new Responce("Found", true, groups)).orElseGet(() -> new Responce("not found", false));
    }

    public Responce deleteOne(Integer id) {
        if (groupRepository.findById(id).isPresent()) {
            Optional<Groups> byId = groupRepository.findById(id);
            Groups groups = byId.get();
            // guruh o'chsa shunga bo'gliq narsalar ham o'chadi !
            groupRepository.deleteById(id);

            for (Teacher teacher : teacherRrepository.findAll()) {
                if (teacher.getGroups().contains(groups)){
                    teacher.getGroups().remove(groups);
                }
            }



            return new Responce("deleted", true, groupRepository.findById(id).get());
        }
        return new Responce("Not found", false);
    }

    public Responce add(GroupDTO dto) {
        // checking already exist or not :
        String new_group_name = dto.getNew_group_name();
        String school_name = dto.getSchool_name();

        boolean canAdd = true;

        for (Groups groups : groupRepository.findAll()) {
            if (groups.getName().equals(new_group_name))
            {
                canAdd = false;
                break;
            }
        }
        if (canAdd) {
            // agar bunday maktab bo'lmasa uni ham  qo'shib qo'yadi
            boolean can_add = true;
            for (School school : schoolRrepository.findAll()) {
                if (school.getName().equals(school_name)){
                    can_add = false ;
                    break;
                }
            }
            if (can_add) {
                schoolService.add(new SchoolDTO(school_name));
            }
            Optional<School> school =
                    schoolRrepository.findByName(school_name);
            School school1 = null;
            if (school.isPresent()) {
                 school1 = school.get();
            }

            Groups groups = new Groups(new_group_name, school1);
            Groups save = groupRepository.save(groups);
            return new Responce("Added",true,save);
        }
        return new Responce("Something  went wrong",false);
    }

    public Responce edit(Integer id, GroupDTO dto) {
        // avval shunaqa group bormi ? keyin shunaqa maktab bormi ?
        // checking already exist or not :
        String new_group_name = dto.getNew_group_name();
        String school_name = dto.getSchool_name();

        Optional<Groups> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Groups group = optionalGroup.get();
            Optional<School> school = schoolRrepository.findByName(school_name);

            if (school.isPresent()) {
                School school1 = school.get();
                group.setName(new_group_name);
                group.setSchool(school1);
                Groups save = groupRepository.save(group);
                return new Responce("Edited",true,save);
            }
        }
        return new Responce("Something went wrong",false);
    }
}
