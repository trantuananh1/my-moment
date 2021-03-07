package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Post;
import com.hunganh.mymoment.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PostRepository extends Neo4jRepository<Post, String> {
}
