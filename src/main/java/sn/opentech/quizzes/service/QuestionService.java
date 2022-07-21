package sn.opentech.quizzes.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sn.opentech.quizzes.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {
    
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

}