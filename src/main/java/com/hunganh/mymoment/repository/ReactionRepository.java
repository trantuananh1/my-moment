package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Reaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReactionRepository extends Neo4jRepository<Reaction, String> {
}
