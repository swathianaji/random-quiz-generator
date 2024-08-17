package com.swathi.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String quizTitle;
    private Integer score;

    @ElementCollection
    private List<Integer> questionIds;
}
