package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.payload.TimeTableDTO;
import uz.pdp.lesson7.repository.TimeTableRepository;
import uz.pdp.lesson7.service.TimeTableService;

@RestController
@RequestMapping(value = "/time_table")
public class TimeTableController {

    // data_source:
    @Autowired
    TimeTableRepository timeTableRepository;
    @Autowired
    TimeTableService timeTableService;

    // get all :
    @GetMapping
    public Responce all(){
        return timeTableService.all();
    }

    @GetMapping(value = "/{id}")
    public Responce one(@PathVariable Integer id){
        return timeTableService.one(id);
    }

    @DeleteMapping(value = "/{id}")
    public Responce delete(@PathVariable Integer id){
        return timeTableService.delete(id);
    }

    @PostMapping
    public Responce add(@RequestBody TimeTableDTO dto){
        return timeTableService.add(dto);
    }

    //editing :
    @PutMapping(value = "/{id}")
    public Responce edit(@PathVariable Integer id,@RequestBody TimeTableDTO dto){
        return timeTableService.edit(id,dto);
    }

}
