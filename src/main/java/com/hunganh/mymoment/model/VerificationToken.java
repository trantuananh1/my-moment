package com.hunganh.mymoment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node
public class VerificationToken {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String token;
    private long expiryDate;

    @Relationship(type = "BELONG_USER")
    private User user;
}
