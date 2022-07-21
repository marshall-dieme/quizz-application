package sn.opentech.quizzes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.opentech.quizzes.model.User;
import sn.opentech.quizzes.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    
    public VerificationToken findByToken(String token);

    public VerificationToken findByUser(User user);
}