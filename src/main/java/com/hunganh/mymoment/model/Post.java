package com.hunganh.mymoment.model;

import com.sn.appbase.model.SnwObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Post extends SnwObject {
    private String caption;
    private float latitude;
    private float longitude;
    private String imagePath;
    private String imageSize;

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

