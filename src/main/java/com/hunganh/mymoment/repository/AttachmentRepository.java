package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.Attachment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 05/04/2021 6:00 PM
 **/

public interface AttachmentRepository extends Neo4jRepository<Attachment, String> {
}
