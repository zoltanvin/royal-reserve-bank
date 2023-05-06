package com.royal.reserve.bank.account.api.repository;

import com.royal.reserve.bank.account.api.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
