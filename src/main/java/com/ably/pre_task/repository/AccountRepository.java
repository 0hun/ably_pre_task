package com.ably.pre_task.repository;

import com.ably.pre_task.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAndPassword(String email, String password);

}
