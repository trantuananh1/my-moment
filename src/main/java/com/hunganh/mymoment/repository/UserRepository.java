package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("MATCH (n:User {username: $username}) RETURN n")
    Optional<User> existsForUsername(String username);

    boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
