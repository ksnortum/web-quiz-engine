package engine.service;

import engine.model.Quiz;
import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<Quiz> getAllQuizzes(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return repository.findAll(paging);
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
