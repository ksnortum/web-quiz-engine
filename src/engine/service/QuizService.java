package engine.service;

import engine.model.Quiz;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QuizService {
    Optional<Quiz> findById(Long id);
    Page<Quiz> getAllQuizzes(Integer pageNo, Integer pageSize, String sortBy);
    Quiz save(Quiz quiz);
    void deleteById(Long id);
}
