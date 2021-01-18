package engine.service;

import engine.model.Completion;
import org.springframework.data.domain.Page;

public interface CompletionService {
    Completion save(Completion completion);
    Page<Completion> getAllCompletions(Integer pageNo, Integer pageSize, String sortBy, String accountId);
}
