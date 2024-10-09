package com.dev.graylien.advice;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.dev.graylien.image.ImageNotFoundException;

@Service
public class AdviceService {
    AdviceRepository adviceRepository;
    AdviceMapper adviceMapper;
    Random random = new Random();

    public AdviceService(AdviceRepository valueTipsRepository, AdviceMapper adviceMapper){
        this.adviceRepository = valueTipsRepository;
        this.adviceMapper = adviceMapper;
    }

    List<AdviceDTO> getAll() {
        return adviceRepository.findAll().stream().map(entity -> adviceMapper.toDTO(entity)).toList();
    }

    Optional<AdviceDTO> getById(Integer id) {
        return adviceRepository.findById(id).map(adviceMapper::toDTO);
    }

    AdviceEntity getRandom() {
        Integer index = (int) random.nextLong(adviceRepository.count());
        Optional<AdviceEntity> tip = adviceRepository.findById(index);
        return tip.get();
    }

    void deleteAll() {
        adviceRepository.deleteAll();
    }

    void delete(Integer id) {
        adviceRepository.deleteById(id);
    }

    void update(AdviceDTO advice, Integer id) {
        Optional<AdviceEntity> existingAdvice = adviceRepository.findById(id);
        if (existingAdvice.isPresent()) {
            AdviceEntity updatedImage = existingAdvice.get();
            updatedImage.setText(advice.text());
        } else {
            throw new ImageNotFoundException();
        }
    }

    void addOne(AdviceDTO advice) {
        adviceRepository.save(adviceMapper.toEntity(advice));
    }
}
