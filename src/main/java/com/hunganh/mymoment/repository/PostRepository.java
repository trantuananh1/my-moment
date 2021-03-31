package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PostRepository extends Neo4jRepository<Post, String> {
}
