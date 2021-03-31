package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.Hashtag;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HashtagRepository extends Neo4jRepository<Hashtag, String> {
}
