package engine.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import engine.model.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
