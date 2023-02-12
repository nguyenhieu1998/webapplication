package com.mywed.repository;

import com.mywed.model.Account;
import com.mywed.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Long countById(Integer id);


}
