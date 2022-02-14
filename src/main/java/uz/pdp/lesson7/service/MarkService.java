package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Mark;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.MarkRrepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    @Autowired
    MarkRrepository markRrepository;


    public Responce all() {
        List<Mark> all = markRrepository.findAll();
        if (all.isEmpty()) {
            return new Responce("No thing found", true); // doim bajaradi shunga true
        }
        return new Responce("hammasi", true, markRrepository.findAll());
    }

    public Responce one(Integer id) {
        Optional<Mark> optionalMark = markRrepository.findById(id);
        return optionalMark.map(mark -> new Responce("Found", true, mark)).orElseGet(() -> new Responce("Not found", false));

    }

    public Responce deleteOne(Integer id) {
        Optional<Mark> byId = markRrepository.findById(id);
        if (byId.isPresent()) {
            markRrepository.deleteById(id);
            return new Responce("deleted",true,byId.get());
        }
        return new Responce("No found",false);
    }

    public Responce add(Integer value) {
        boolean canAdd = true;
        for (Mark mark : markRrepository.findAll()) {
            if (mark.getValue().equals(value)) {
                canAdd = false;
                break;
            }
        }
        if (canAdd) {
            Mark mark = new Mark(value);
            Mark save = markRrepository.save(mark);
            return new Responce("Added",true,save);
        }
        return new Responce("This is already exists",false);
    }

    public Responce edit(Integer id, Integer value) {
        Optional<Mark> optionalMark = markRrepository.findById(id);
        if (optionalMark.isPresent()) {
            optionalMark.get().setValue(value);
            return new Responce("Edited",true,optionalMark.get());
        }
        return new Responce("Not found",false);
    }
}
