package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    public Optional<Student> findByName(String name);
}
