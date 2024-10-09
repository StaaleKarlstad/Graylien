package com.dev.graylien.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/")
    ImageDTO getRandom(){
        return imageService.getRandom();
    }

    @GetMapping("/images")
    List<ImageDTO> getAll(){
        return imageService.getAll();
    }

    @GetMapping("/image/{id}")
    ImageDTO getOne(@PathVariable Integer id){

        Optional<ImageDTO> image = imageService.getById(id);
        if (image.isEmpty()){
            throw new ImageNotFoundException();
        }
        return image.get();
    }

    @DeleteMapping("/images")
    void deleteAll(){
        imageService.deleteAll();
    }

    @DeleteMapping("/image/{id}")
    void delete(@PathVariable Integer id){
        imageService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/image")
    void create (@Valid @RequestBody ImageDTO image){
        imageService.addOne(image);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/image/{id}")
    void update(@Valid @RequestBody ImageDTO image, @PathVariable Integer id){
        imageService.update(image, id);
    }




}
