package com.hunganh.mymoment.model.object;

import com.sn.appbase.model.SnwObject;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Post extends SnwObject {
    private String caption;
    private float latitude;
    private float longitude;
    private List<String> imageUrls;

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

