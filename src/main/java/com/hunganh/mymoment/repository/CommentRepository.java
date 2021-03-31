package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepository extends Neo4jRepository<Comment, String> {
}
