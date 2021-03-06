package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.VerificationToken;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends Neo4jRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
