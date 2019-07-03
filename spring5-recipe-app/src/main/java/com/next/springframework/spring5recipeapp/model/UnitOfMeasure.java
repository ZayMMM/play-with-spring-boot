package com.next.springframework.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}
