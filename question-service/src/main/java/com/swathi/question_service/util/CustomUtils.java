package com.swathi.question_service.util;

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
            qw.setOptions(q.getOptions());
            questionWrappers.add(qw);
        }
        return questionWrappers;
    }
}
