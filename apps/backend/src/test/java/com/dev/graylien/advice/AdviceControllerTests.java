package com.dev.graylien.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerTests {

    @InjectMocks
    private AdviceController adviceController;

    @Mock
    private AdviceService adviceService;

    // addByID
    @Test
    public void shouldCallGetById() {
        when(adviceService.getById(any())).thenReturn(Optional.of(new AdviceDTO(1, "text")));
        adviceController.getById(1);
        verify(adviceService, times(1)).getById(any());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNotFound() {
        when(adviceService.getById(any())).thenReturn(Optional.empty());
        assertThrows(AdviceNotFoundException.class, () -> {
            adviceController.getById(99);
        });
    }

    // addAll
    @Test
    public void shouldCallGetAll() {
        List<AdviceDTO> response = new ArrayList<>();
        response.add(new AdviceDTO(1, "test"));

        when(adviceService.getAll()).thenReturn(response);
        List<AdviceDTO> dtos = adviceController.getAll();

        assertEquals(dtos.size(), response.size());
        verify(adviceService, times(1)).getAll();
    }

    // update
    @Test
    public void shouldCallUpdate() {
        AdviceDTO dto = new AdviceDTO(1, "test");
        when(adviceService.update(dto, 1)).thenReturn(dto);
        adviceController.update(1, dto);
        verify(adviceService, times(1)).update(dto, 1);
    }

    // delete
    @Test
    public void shouldCallDelete() {
        doNothing().when(adviceService).delete(any());
        adviceController.delete(1);
        verify(adviceService, times(1)).delete(1);
    }

    // deleteAll
    @Test
    public void shouldCallDeleteAll() {
        doNothing().when(adviceService).deleteAll();
        adviceController.deleteAll();
        verify(adviceService, times(1)).deleteAll();
    }

    // create
}
