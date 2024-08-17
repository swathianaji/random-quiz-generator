package com.swathi.question_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String category;
    private String rightAnswer;
    private String difficultyLevel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> options;

}
