package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagRepository extends Neo4jRepository<Tag, String> {
}
