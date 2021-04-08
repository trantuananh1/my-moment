package com.hunganh.mymoment.repository.assoc;

import com.hunganh.mymoment.model.assoc.VerificationOwnership;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VerificationOwnershipRepository extends Neo4jRepository<VerificationOwnership, String> {

}
