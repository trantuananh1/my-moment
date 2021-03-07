package com.hunganh.mymoment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Tag {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;

    @Relationship(type = "HAS_POST")
    private Set<Post> posts;
}
