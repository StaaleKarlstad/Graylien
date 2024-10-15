package com.dev.graylien.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dev.graylien.image.ImageNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AdviceServiceTests {

    @InjectMocks
    private AdviceService adviceService;

    @Mock
    private AdviceMapper adviceMapper;
    @Mock
    private AdviceRepository adviceRepository;

    // Create
    @Test
    public void shouldSuccesfullySaveAnAdvice() {
        CreateAdviceDTO mockDTO = new CreateAdviceDTO("Test");
        AdviceEntity mockEntity = new AdviceEntity(1, "Test");
        AdviceEntity savedEntity = new AdviceEntity(1, "Test");

        Mockito.when(adviceMapper.toEntityFromCreateDTO(mockDTO)).thenReturn(mockEntity);
        Mockito.when(adviceRepository.save(mockEntity)).thenReturn(savedEntity);
        Mockito.when(adviceMapper.toDTO(savedEntity))
                .thenReturn(new AdviceDTO(savedEntity.getId(), savedEntity.getText()));

        AdviceDTO response = adviceService.addOne(mockDTO);

        assertEquals(mockDTO.text(), response.text());

        verify(adviceMapper, times(1)).toEntityFromCreateDTO(mockDTO);
        verify(adviceRepository, times(1)).save(mockEntity);
        verify(adviceMapper, times(1)).toDTO(savedEntity);
    }

    // findAll
    @Test
    public void shouldReturnAllAdvice() {
        List<AdviceEntity> advice = new ArrayList<>();
        advice.add(new AdviceEntity(1, "test"));

        when(adviceRepository.findAll()).thenReturn(advice);
        when(adviceMapper.toDTO(any(AdviceEntity.class))).thenReturn(new AdviceDTO(1, "test"));

        List<AdviceDTO> adviceDtos = adviceService.getAll();
        assertEquals(advice.size(), adviceDtos.size());
        verify(adviceRepository, times(1)).findAll();
    }

    // findById
    @Test
    public void shouldReturnTheSelectedId() {
        AdviceEntity entity = new AdviceEntity(1, "test");
        AdviceDTO responseDto = new AdviceDTO(1, "test");

        when(adviceRepository.findById(1)).thenReturn(Optional.of(entity));
        when(adviceMapper.toDTO(any(AdviceEntity.class))).thenReturn(responseDto);

        adviceService.getById(1);

        verify(adviceRepository, times(1)).findById(any());
    }

    @Test
    public void shouldReturnEmptyOptionalWhenIdNotFound() {
        when(adviceRepository.findById(any())).thenReturn(Optional.empty());

        Optional<AdviceDTO> optional = adviceService.getById(999);

        assertTrue(optional.isEmpty());
        verify(adviceRepository, times(1)).findById(any());
    }

    @Test
    public void shouldCallDeleteMethodInRepository() {
        Integer adviceId = 1;

        adviceService.delete(adviceId);

        verify(adviceRepository, times(1)).deleteById(adviceId);
    }

    @Test
    public void shouldCallDeleteAllMethodInRepository() {
        adviceService.deleteAll();

        verify(adviceRepository, times(1)).deleteAll();
    }

    @Test
    public void shouldReturnUpdatedAdviceDTO() {
        AdviceDTO dto = new AdviceDTO(2, "Text");
        AdviceEntity entity = new AdviceEntity(2, "Update");
        AdviceDTO updatedDto = new AdviceDTO(2, "Update");

        when(adviceRepository.findById(2)).thenReturn(Optional.of(entity));
        when(adviceMapper.toDTO(entity)).thenReturn(updatedDto);

        AdviceDTO responseDto = adviceService.update(dto, 2);

        assertEquals(updatedDto.text(), responseDto.text());

        verify(adviceRepository, times(1)).findById(2);
        verify(adviceMapper, times(1)).toDTO(entity);
    }

    @Test
    public void shouldThrowExceptionWhenAdviceToUpdateIsNotFound() {
        when(adviceRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ImageNotFoundException.class, () -> {
            adviceService.update(any(), 999);
        });
    }

    // getRandom

}
