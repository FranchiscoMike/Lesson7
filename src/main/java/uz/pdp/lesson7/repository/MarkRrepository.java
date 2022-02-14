package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Mark;
import uz.pdp.lesson7.entity.School;

public interface MarkRrepository extends JpaRepository<Mark,Integer> {
}
