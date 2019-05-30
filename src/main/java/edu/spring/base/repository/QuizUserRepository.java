package edu.spring.base.repository;

import edu.spring.base.model.QuizUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, Long> {

    QuizUser findByUsername(String username);
}
