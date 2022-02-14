package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.TeacherDTO;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.repository.SubjectRrepository;
import uz.pdp.lesson7.repository.TeacherRrepository;
import uz.pdp.lesson7.service.TeacherService;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    //useful:

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherRrepository teacherRrepository;

    @Autowired
    SubjectRrepository subjectRrepository;

    @Autowired
    GroupRepository groupRepository;

    // hammasi :
    @GetMapping
    public Responce all(){
        return teacherService.all();
    }

    // bittasi :
    @GetMapping(value = "/{id}")
    public Responce one(@PathVariable Integer id){
        return teacherService.one(id);
    }

    //delete
    @DeleteMapping(value = "/{id}")
    public Responce delete(@PathVariable Integer id){
        return teacherService.delete(id);
    }

    //adding to techer :
    @PostMapping
    public Responce add(@RequestBody TeacherDTO dto){
        return teacherService.add(dto);
    }

    // editing data:
    @PutMapping(value = "/{id}")
    public Responce edit(@PathVariable Integer id,@RequestBody TeacherDTO dto){
        return teacherService.edit(id,dto);
    }

}
