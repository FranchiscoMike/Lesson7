package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.SubjectRrepository;
import uz.pdp.lesson7.service.SubjectService;

@RestController
@RequestMapping(value = "/subject")
public class SubjecController {

    @Autowired
    SubjectRrepository subjectRrepository;

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public Responce all(){
        return subjectService.all();
    }

    @GetMapping("/{id}")
    public Responce all(@PathVariable Integer id){
        return subjectService.one(id);
    }

     @DeleteMapping("/{id}")
    public Responce delete(@PathVariable Integer id){
        return subjectService.delete(id);
    }

     @PutMapping("/{id}")
    public Responce edit(@PathVariable Integer id,String new_subject_name){
        return subjectService.edit(id,new_subject_name);
    }

    @PostMapping
    public Responce add(String new_subject_name){
        return  subjectService.add(new_subject_name);
    }




}
