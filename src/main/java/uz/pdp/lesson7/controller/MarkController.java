package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.entity.Mark;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.MarkRrepository;
import uz.pdp.lesson7.service.MarkService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mark")
public class MarkController {

    @Autowired
    MarkRrepository markRrepository;

    @Autowired
    MarkService markService;

    @GetMapping
    public Responce all(){
        // avval tekshirish ham kerak u bo'shmi yo'qmi deb :
        return markService.all();
    }

    @GetMapping(value = "/{id}")
    public Responce one(@PathVariable Integer id){
        return markService.one(id);
    }

    @DeleteMapping(value = "{id}")
    public Responce deleteOne(@PathVariable Integer id){
        return markService.deleteOne(id);
    }

    @PostMapping
    public Responce add(Integer value){
        return markService.add(value);
    }

    @PutMapping(value = "{id}")
    public Responce edit(@PathVariable Integer id,Integer value){
        return markService.edit(id,value);
    }
}
