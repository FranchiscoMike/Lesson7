package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Student;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.StudentDTO;
import uz.pdp.lesson7.repository.SchoolRrepository;
import uz.pdp.lesson7.repository.StudentRepository;
import uz.pdp.lesson7.service.StudentService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    // nasib bo'lsa eng mukammal student service bo'ladi
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public Responce all() {
        return studentService.all();
    }

    @GetMapping(value = "/{id}")
    public Responce one(@PathVariable Integer id) {
        return studentService.one(id);
    }

    @DeleteMapping(value = "/{id}")
    public Responce delete(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    @PostMapping
    public Responce add(@RequestBody StudentDTO dto) {
        return studentService.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Responce edit(@PathVariable Integer id ,@RequestBody StudentDTO dto) {
        return studentService.edit(dto);
    }


}
