package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Tag;
import com.hunganh.mymoment.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagRepository extends Neo4jRepository<Tag, String> {
}
