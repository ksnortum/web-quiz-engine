package engine.persistence;

import engine.model.Completion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompletionRepository extends PagingAndSortingRepository<Completion, Long> {
    @Query("SELECT c FROM Completion c WHERE c.accountId = :accountId")
    Page<Completion> findAllCompletionsByAccount(Pageable paging, @Param("accountId") String accountId);
}
