package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.VerificationToken;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends Neo4jRepository<VerificationToken, String> {
    Optional<VerificationToken> findByToken(String token);
}
