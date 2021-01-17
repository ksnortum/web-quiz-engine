package engine.persistence;

import engine.model.Completion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, Long> {
    @Query("SELECT c FROM Completion c WHERE c.accountId = :accountId")
    List<Completion> findAllCompletionsByAccount(@Param("accountId") String accountId);
}
