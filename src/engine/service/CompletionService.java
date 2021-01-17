package engine.service;

import engine.model.Completion;

import java.util.List;

public interface CompletionService {
    Completion save(Completion completion);
    List<Completion> findAllCompletionsByAccount(String accountId);
}
