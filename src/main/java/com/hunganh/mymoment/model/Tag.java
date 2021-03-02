package com.hunganh.mymoment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Tag {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Relationship(type = "BELONG_POST")
    private Post post;
}
