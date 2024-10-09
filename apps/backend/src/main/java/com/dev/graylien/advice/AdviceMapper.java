package com.dev.graylien.advice;

import org.springframework.stereotype.Component;

@Component
public class AdviceMapper {
    
    public AdviceDTO toDTO(AdviceEntity entity){
        return new AdviceDTO(entity.getId(), entity.getText());

    }

    public AdviceEntity toEntity(AdviceDTO dto){
        return new AdviceEntity(dto.id(), dto.text());
    }
}
