package sn.opentech.quizzes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.opentech.quizzes.model.Quizz;

@Repository
public interface QuizzRepository extends JpaRepository<Quizz, Integer> {
    
}