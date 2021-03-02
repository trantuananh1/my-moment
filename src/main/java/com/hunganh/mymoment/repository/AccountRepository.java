package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Account;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccountRepository extends Neo4jRepository<Account, Long> {
    Account findByUsername(String username);
}
