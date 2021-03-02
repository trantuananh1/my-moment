package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Profile;
import com.hunganh.mymoment.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProfileRepository extends Neo4jRepository<Profile, Long> {
}
