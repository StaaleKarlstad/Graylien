package com.dev.graylien.image;

import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageDTO toDTO(ImageEntity entity){
        return new ImageDTO(entity.getId(), entity.getTitle(), entity.getCategory(), entity.getUrl());
    }

    public ImageEntity toEntity(ImageDTO dto){
        return new ImageEntity(dto.title(), dto.category(), dto.url());
    }
}
