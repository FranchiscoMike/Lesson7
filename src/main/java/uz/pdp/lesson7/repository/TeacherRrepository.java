package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Mark;
import uz.pdp.lesson7.entity.Teacher;

import java.util.Optional;

public interface TeacherRrepository extends JpaRepository<Teacher,Integer> {
    public Optional<Teacher> findByName(String name);
}
