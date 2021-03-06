package engine.service;

import engine.model.Account;

public interface AccountService {
    boolean existsByEmail(String email);
    Account save(Account account);
}
