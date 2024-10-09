package com.dev.graylien.image;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
    ImageRepository imageRepository;
    ImageMapper imageMapper;
    Random random = new Random();

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    List<ImageDTO> getAll() {
        return imageRepository.findAll().stream().map(entity -> imageMapper.toDTO(entity)).toList();
    }

    Optional<ImageDTO> getById(Integer id) {
        Optional<ImageEntity> optEntity = imageRepository.findById(id);
        return optEntity.map(imageMapper::toDTO);
    }

    ImageDTO getRandom() {
        Integer index = (int) random.nextLong(imageRepository.count());
        Optional<ImageDTO> img = getById(index);
        return img.get();
    }

    void deleteAll() {
        imageRepository.deleteAll();
    }

    void delete(Integer id) {
        imageRepository.deleteById(id);
    }

    void update(ImageDTO image, Integer id) {
        Optional<ImageEntity> img = imageRepository.findById(id);
        if (img.isPresent()) {
            ImageEntity updatedImage = img.get();
            updatedImage.setTitle(image.title());
            updatedImage.setUrl(image.url());
            updatedImage.setCategory(image.category());
            imageRepository.save(updatedImage);
        } else {
            throw new ImageNotFoundException();
        }
    }

    void addOne(ImageDTO image) {
        imageRepository.save(imageMapper.toEntity(image));
    }
}
