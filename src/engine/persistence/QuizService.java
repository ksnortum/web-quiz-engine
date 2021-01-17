package engine.persistence;

import engine.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Optional<Quiz> findById(Long id);
    List<Quiz> findAll();
    Quiz save(Quiz quiz);
    void deleteById(Long id);
}
