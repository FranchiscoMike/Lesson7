package uz.pdp.lesson7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.lesson7.entity.Attachment;
import uz.pdp.lesson7.entity.AttachmentContent;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.AttachmentContentRepository;
import uz.pdp.lesson7.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {


   final AttachmentRepository attachmentRepository;
   final AttachmentContentRepository attachmentContentRepository;



    public Responce upload(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();


        // fayllar kk bo'lsa :
        List<MultipartFile> files = request.getFiles(fileNames.next());

        for (MultipartFile file : files) {

            Attachment attachment = new Attachment();

            byte[] bytes = file.getBytes();
            String contentType = file.getContentType();
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();

            attachment.setName(originalFilename);
            attachment.setSize(size);
            attachment.setType(contentType);

            Attachment save = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();

            attachmentContent.setAttachment(save);
            attachmentContent.setBytes(bytes);
            attachmentContentRepository.save(attachmentContent);

        }

        // faylning nomalarini aniqlash :
//        while (fileNames.hasNext()) {
//            request.getFile(fileNames.next());
//            request.
//        }


        return null;
    }

    public HttpEntity<?> download(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();


        }
        return null;
    }
}
