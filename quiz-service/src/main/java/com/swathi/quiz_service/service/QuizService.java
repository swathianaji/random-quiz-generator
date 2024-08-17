package com.swathi.quiz_service.service;

import com.swathi.quiz_service.dao.QuizDao;
import com.swathi.quiz_service.feign.QuizInterface;
import com.swathi.quiz_service.model.QuestionWrapper;
import com.swathi.quiz_service.model.Quiz;
import com.swathi.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String quizTitle, String category, Integer numQuestions) {
        try{
            List<Integer> questionIds = quizInterface.generateQuestionsForQuiz(category, numQuestions).getBody();
            Quiz quiz = new Quiz();
            quiz.setQuizTitle(quizTitle);
            quiz.setQuestionIds(questionIds);
            quizDao.save(quiz);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(Integer id) {
        try{
            Quiz quiz = quizDao.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsById(questionIds).getBody();
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
        try{
            Integer score = quizInterface.calculateScore(responses).getBody();
            Quiz quiz = quizDao.findById(id).get();
            quiz.setScore(score);
            quizDao.save(quiz);
            return new ResponseEntity<>(score, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
