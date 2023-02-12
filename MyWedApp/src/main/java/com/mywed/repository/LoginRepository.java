package com.mywed.repository;

import com.mywed.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);
}
