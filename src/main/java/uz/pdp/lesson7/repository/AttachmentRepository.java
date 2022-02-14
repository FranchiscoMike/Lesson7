package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Attachment;
import uz.pdp.lesson7.entity.TimeTable;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
