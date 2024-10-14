package com.dev.graylien.advice;

import org.springframework.stereotype.Component;

@Component
public class AdviceMapper {
    
    public AdviceDTO toDTO(AdviceEntity entity){
        if (entity==null){
            throw new IllegalArgumentException("Can't map null value to AdviceDTO type");
        }
        return new AdviceDTO(entity.getId(), entity.getText());

    }

    public AdviceEntity toEntity(AdviceDTO dto){
        if (dto==null){
            throw new IllegalArgumentException("Can't map null value to AdviceEntity type");
        }
        return new AdviceEntity(dto.id(), dto.text());
    }

    public AdviceEntity toEntityFromCreateDTO(CreateAdviceDTO dto){
        if (dto==null){
            throw new IllegalArgumentException("Can't map null value to AdviceEntity type");
        }
        return new AdviceEntity(null, dto.text());
    }
}
