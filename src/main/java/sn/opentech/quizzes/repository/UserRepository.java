package sn.opentech.quizzes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.opentech.quizzes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}