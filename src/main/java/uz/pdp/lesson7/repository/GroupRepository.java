package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Groups;

import javax.swing.*;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Groups,Integer> {
    public Optional<Groups> findByName(String name);
}
