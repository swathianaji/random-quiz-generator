package com.swathi.question_service.service;

import com.swathi.question_service.dao.QuestionDao;
import com.swathi.question_service.model.Question;
import com.swathi.question_service.model.QuestionWrapper;
import com.swathi.question_service.util.CustomUtils;
import com.swathi.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<QuestionWrapper>> getAllQuestions() {
        try {
            List<Question> questions = questionDao.findAll();
            List<QuestionWrapper> questionWrappers = CustomUtils.wrapQuestions(questions);
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByCategory(String category) {
        try {
            List<Question> questions = questionDao.findAllByCategory(category);
            List<QuestionWrapper> questionWrappers = CustomUtils.wrapQuestions(questions);
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer numQuestions) {
        try {
            List<Integer> questionIds = questionDao.generateQuestionsForQuiz(category, numQuestions);
            return new ResponseEntity<>(questionIds, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
        try {
            List<Question> questions = questionDao.findAllById(questionIds);
            List<QuestionWrapper> questionWrappers = CustomUtils.wrapQuestions(questions);
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        try{
            int score = 0;
            for(Response r: responses){
                Question q = questionDao.findById(r.getId()).get();
                if(q.getRightAnswer().equals(r.getAnswer())){
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
