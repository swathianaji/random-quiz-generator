package com.swathi.question_service.util;

import com.swathi.question_service.model.Option;
import com.swathi.question_service.model.Question;
import com.swathi.question_service.model.QuestionWrapper;

import java.util.ArrayList;
import java.util.List;

public class CustomUtils {
    public static List<QuestionWrapper> wrapQuestions(List<Question> questions){
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question q: questions){
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());
            List<Option> options = q.getOptions();
            List<String> optionsContent = new ArrayList<>();
            for(Option o: options){
                optionsContent.add(o.getContent());
            }
            qw.setOptions(optionsContent);
            questionWrappers.add(qw);
        }
        return questionWrappers;
    }
}
