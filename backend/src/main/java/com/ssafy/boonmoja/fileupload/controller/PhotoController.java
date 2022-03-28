package com.ssafy.boonmoja.fileupload.controller;

import com.ssafy.boonmoja.fileupload.model.Photo;
import com.ssafy.boonmoja.fileupload.repository.PhotoRepository;
import com.ssafy.boonmoja.fileupload.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
//@PreAuthorize("hasRole('USER')")
public class PhotoController {

    private final PhotoRepository photoRepository;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        // 프론트에서 나타내기 위해 byte[] 로 반환된 이미지 데이터를 Base64 문자열로 변환
        return "photos";
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }

    @PostMapping("/photos/add")
    //@RequestParam("title") String title, @RequestParam("image")
    //@RequestPart PhotoDto photoDto, @RequestPart MultipartFile imgFile
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile imgFile, Model model) throws IOException {
//        String id = photoService.addPhoto(title, image);
        String id = photoService.addPhoto(title, imgFile);
////        return "redirect:/photos/" + id;
//        System.out.println(photoDto.getTitle());
        log.info("photoTest: {},{}",id,title);
//        return id;

        return "redirect:/photos/" + id;
    }

    @GetMapping("/photos")
    public List<Photo> getPhotos(){
        if(photoRepository.findAll()==null) return null;
        return photoRepository.findAll();
    }
}
