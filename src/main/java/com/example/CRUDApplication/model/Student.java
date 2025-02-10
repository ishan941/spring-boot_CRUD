package com.example.CRUDApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //
@Data // Getter setter
@Entity // Creates orm
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto degenrate
    private int id;
    private String name;
    private int rollNo;
    private int grade;
}
