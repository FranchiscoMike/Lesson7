package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Attachment;
import uz.pdp.lesson7.entity.AttachmentContent;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends
        JpaRepository<AttachmentContent, UUID> {

    public Optional<Attachment> findByAttachment(Integer id);

}
