package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Hashtag;
import com.hunganh.mymoment.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HashtagRepository extends Neo4jRepository<Hashtag, Long> {
}
