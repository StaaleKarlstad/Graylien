package com.dev.graylien.advice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdviceMapperTests {

    AdviceMapper adviceMapper;

    @BeforeEach
    void setUp(){
        adviceMapper = new AdviceMapper();
    }

    @Test
    public void shouldMapDtoToEntity(){
        AdviceDTO dto = new AdviceDTO(1, "Test");
        AdviceEntity entity = adviceMapper.toEntity(dto);

        Assertions.assertEquals(dto.id(), entity.getId());
        Assertions.assertEquals(dto.text(), entity.getText());

    }

    @Test
    public void shouldMapEntityToDto(){
        AdviceEntity entity = new AdviceEntity(1, "text");
        AdviceDTO dto = adviceMapper.toDTO(entity);

        Assertions.assertEquals(entity.getId(), dto.id());
        Assertions.assertEquals(entity.getText(), dto.text());
    }

    @Test
    public void toDtoShouldThrowNullPointerExceptionWhenEntityIsNull(){
        var exp = Assertions.assertThrows(IllegalArgumentException.class, () -> adviceMapper.toDTO(null));
        Assertions.assertEquals("Can't map null value to AdviceDTO type",exp.getMessage());
    }

    @Test
    public void toEntityShouldThrowNullPointerExceptionWhenEntityIsNull(){
        var exp = Assertions.assertThrows(IllegalArgumentException.class, () -> adviceMapper.toEntity(null));
        Assertions.assertEquals("Can't map null value to AdviceEntity type",exp.getMessage());
    }
}
