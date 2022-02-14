package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.School;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.SchoolDTO;
import uz.pdp.lesson7.repository.SchoolRrepository;
import uz.pdp.lesson7.service.SchoolService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    @Autowired
    SchoolRrepository schoolRrepository;
    @Autowired
    SchoolService schoolService;
    @GetMapping
    public List getSchools() {
        return schoolRrepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public School getOne(@PathVariable Integer id) {
        Optional<School> optionalSchool = schoolRrepository.findById(id);
        return optionalSchool.orElseGet(School::new);
    }

    @DeleteMapping(value = "/{id}")
    public Responce deleteOne(@PathVariable Integer id) {

        return schoolService.delete(id);

    }

    @PostMapping
    public Responce addSchool(@RequestBody SchoolDTO dto) {
        return schoolService.add(dto);

    }

    @PutMapping(value = "/{id}")
    public Responce editSchool(@PathVariable Integer id, @RequestBody SchoolDTO dto){
       return schoolService.edit(id,dto);
      }


}
