package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProfileRepository extends Neo4jRepository<Profile, String> {
}
