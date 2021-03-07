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
public class Post {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String caption;
    private float latitude;
    private float longitude;
    private String imagePath;
    private String imageSize;
    private long dateCreated;
    private long dateUpdated;

    @Relationship(type = "BELONG_USER")
    private User user;
    @Relationship(type = "HAS_HASHTAG")
    private Set<Hashtag> hashtags;
    @Relationship(type = "HAS_TAG")
    private Set<Tag> tags;
    @Relationship(type = "HAS_COMMENT")
    private Set<Comment> comments;
    @Relationship(type = "HAS_REACTION")
    private Set<Reaction> reactions;
}

