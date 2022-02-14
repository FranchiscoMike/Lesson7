package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.School;

import java.util.List;
import java.util.Optional;

public interface SchoolRrepository extends JpaRepository<School,Integer> {
    public Optional<School> findByName(String name);
}
