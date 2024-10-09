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
    AdviceService valueTipsService;

    public AdviceController(AdviceService valueTipsService) {
        this.valueTipsService = valueTipsService;
    }

    @GetMapping("/tips")
    public List<AdviceEntity> getAll() {
        return valueTipsService.getAll();
    }

    @GetMapping("/tips/{id}")
    public AdviceEntity getById(@PathVariable Integer id) {
        Optional<AdviceEntity> vte = valueTipsService.getById(id);
        if (vte.isEmpty()) {
            throw new AdviceNotFoundException();
        }
        return vte.get();
    }

    @PostMapping("/tips")
    public void create(AdviceEntity vte) {
        valueTipsService.addOne(vte);
    }

    @PutMapping("/tips/{id}")
    public void update(@PathVariable Integer id, @RequestBody AdviceEntity vte) {
        valueTipsService.update(vte, id);
    }

    @DeleteMapping("tips/{id}")
    public void delete(@PathVariable Integer id) {
        valueTipsService.delete(id);
    }

    @DeleteMapping("tips")
    public void deleteAll() {
        valueTipsService.deleteAll();
    }
}
