package engine.service;

import engine.model.Completion;
import engine.persistence.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletionServiceImpl implements CompletionService {
    @Autowired
    CompletionRepository repository;

    @Override
    public Completion save(Completion completion) {
        return repository.save(completion);
    }

    @Override
    public List<Completion> findAllCompletionsByAccount(String accountId) {
        return repository.findAllCompletionsByAccount(accountId);
    }
}
