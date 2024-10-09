package com.dev.graylien.image;

public record ImageDTO(
    Integer id,
    String title,
    ImageCategory category,
    String url
) {

}
