package engine.persistence;

import engine.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepository repository;

    @Override
    public Optional<Quiz> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Quiz> findAll() {
        return repository.findAll();
    }

    @Override
    public Quiz save(Quiz quiz) {
        return repository.save(quiz);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
