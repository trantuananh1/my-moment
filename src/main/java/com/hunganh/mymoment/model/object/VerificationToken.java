package com.hunganh.mymoment.model.object;

import com.hunganh.mymoment.model.assoc.VerificationOwnership;
import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class VerificationToken {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String token;
    private long expiryDate;
    @Relationship(type = "BELONG_USER")
    private VerificationOwnership verificationOwnerships;
}
