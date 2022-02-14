package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.pdp.lesson7.entity.Subject;

import java.util.Optional;

public interface SubjectRrepository extends JpaRepository<Subject,Integer> {
    public Optional<Subject> findByName(String name);
}
