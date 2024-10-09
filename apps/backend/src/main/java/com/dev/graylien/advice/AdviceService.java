package com.dev.graylien.advice;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.dev.graylien.image.ImageNotFoundException;

@Service
public class AdviceService {
    AdviceRepository valueTipsRepository;
    Random random = new Random();

    public AdviceService(AdviceRepository valueTipsRepository){
        this.valueTipsRepository = valueTipsRepository;
    }

    List<AdviceEntity> getAll() {
        return valueTipsRepository.findAll();
    }

    Optional<AdviceEntity> getById(Integer id) {
        return valueTipsRepository.findById(id);
    }

    AdviceEntity getRandom() {
        Integer index = (int) random.nextLong(valueTipsRepository.count());
        Optional<AdviceEntity> tip = getById(index);
        return tip.get();
    }

    void deleteAll() {
        valueTipsRepository.deleteAll();
    }

    void delete(Integer id) {
        valueTipsRepository.deleteById(id);
    }

    void update(AdviceEntity image, Integer id) {
        Optional<AdviceEntity> img = getById(id);
        if (img.isPresent()) {
            AdviceEntity updatedImage = img.get();
            updatedImage.setText(image.getText());
        } else {
            throw new ImageNotFoundException();
        }
    }

    void addOne(AdviceEntity image) {
        valueTipsRepository.save(image);
    }
}
