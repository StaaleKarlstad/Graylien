package com.dev.graylien.advice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tip")
public class AdviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotBlank
    private String text;

    public AdviceEntity(){
    }

    public AdviceEntity(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
}
