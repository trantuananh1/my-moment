package com.hunganh.mymoment.repository;

import com.hunganh.mymoment.model.object.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

public interface ImageMetadataRepository extends Neo4jRepository<ImageMetadata, String> {

}
