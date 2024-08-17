package com.swathi.question_service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionWrapper {
    private Integer id;
    private String questionTitle;
    private List<String> options;
}
