package com.next.fullstackbookstore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;

    private String first_name;

    private String title;

    private double price;

    private short on_sale;

    private int calendar_year;

    private String description;

    private int inventory;
}
