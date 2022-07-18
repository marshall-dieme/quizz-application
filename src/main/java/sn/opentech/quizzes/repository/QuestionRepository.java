package sn.opentech.quizzes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sn.opentech.quizzes.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT * FROM question ORDER BY rand() LIMIT 20", nativeQuery = true)
    public List<Question> getRandomQuestions();
    
}