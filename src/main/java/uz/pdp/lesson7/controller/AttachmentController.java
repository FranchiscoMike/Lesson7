package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.lesson7.payload.Responce;
import uz.pdp.lesson7.repository.AttachmentRepository;
import uz.pdp.lesson7.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
public class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentService attachmentService;

    // upload
    public Responce download(MultipartHttpServletRequest request) throws IOException {
        return  attachmentService.upload(request);
    }


    // download

    @GetMapping(value = "/{id}")
    public HttpEntity<?> download(@PathVariable Integer id){
        return attachmentService.download(id);
    }


    //getId

}
