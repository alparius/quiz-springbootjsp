package edu.spring.base.service;

import edu.spring.base.model.Question;
import edu.spring.base.repository.QuestionRepository;
import edu.spring.base.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired private QuestionRepository questionRepo;
    @Autowired private QuizUserRepository userRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }
}
