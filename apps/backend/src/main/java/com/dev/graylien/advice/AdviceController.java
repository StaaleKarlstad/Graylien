package com.dev.graylien.advice;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdviceController {
    AdviceService adviceService;

    public AdviceController(AdviceService valueTipsService) {
        this.adviceService = valueTipsService;
    }

    @GetMapping("/tips")
    public List<AdviceDTO> getAll() {
        return adviceService.getAll();
    }

    @GetMapping("/tips/{id}")
    public AdviceDTO getById(@PathVariable Integer id) {
        Optional<AdviceDTO> vte = adviceService.getById(id);
        if (vte.isEmpty()) {
            throw new AdviceNotFoundException();
        }
        return vte.get();
    }

    @PostMapping("/tips")
    public AdviceDTO create(CreateAdviceDTO vte) {
        return adviceService.addOne(vte);
    }

    @PutMapping("/tips/{id}")
    public AdviceDTO update(@PathVariable Integer id, @RequestBody AdviceDTO vte) {
        return adviceService.update(vte, id);
    }

    @DeleteMapping("tips/{id}")
    public void delete(@PathVariable Integer id) {
        adviceService.delete(id);
    }

    @DeleteMapping("tips")
    public void deleteAll() {
        adviceService.deleteAll();
    }
}
