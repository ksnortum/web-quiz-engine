package engine.service;

import engine.model.Completion;
import engine.persistence.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompletionServiceImpl implements CompletionService {
    @Autowired
    CompletionRepository repository;

    @Override
    public Completion save(Completion completion) {
        return repository.save(completion);
    }

    @Override
    public Page<Completion> getAllCompletions(Integer pageNo, Integer pageSize, String sortBy, String accountId) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        return repository.findAllCompletionsByAccount(paging, accountId);
    }
}
