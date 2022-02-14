package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.GroupDTO;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.GroupRepository;
import uz.pdp.lesson7.service.GroupService;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @GetMapping
    public Responce all() {
        return groupService.all();
    }

    @GetMapping(value = "{id}")
    public Responce one(@PathVariable Integer id) {
        return groupService.one(id);
    }

    @DeleteMapping(value = "{id}")
    public Responce deleteOne(@PathVariable Integer id) {
        return groupService.deleteOne(id);
    }

    // adding :
    @PostMapping
    public Responce add(@RequestBody GroupDTO dto) {
        return groupService.add(dto);
    }

    //editing :
      @PutMapping("/{id}")
    public Responce add(@PathVariable Integer id,@RequestBody GroupDTO dto) {
        return groupService.edit(id,dto);
    }
}
