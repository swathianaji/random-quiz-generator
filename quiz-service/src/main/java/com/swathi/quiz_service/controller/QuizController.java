package com.swathi.quiz_service.controller;

import com.swathi.quiz_service.model.QuestionWrapper;
import com.swathi.quiz_service.model.Response;
import com.swathi.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String quizTitle, @RequestParam String category, @RequestParam Integer numQuestions){
        return quizService.createQuiz(quizTitle, category, numQuestions);
    }

    @GetMapping("{id}/start")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@PathVariable Integer id){
        return quizService.getQuestionsForQuiz(id);
    }

    @PostMapping("{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id, responses);
    }
}
