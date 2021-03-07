package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.Comment;
import com.hunganh.mymoment.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CommentRepository extends Neo4jRepository<Comment, String> {
}
