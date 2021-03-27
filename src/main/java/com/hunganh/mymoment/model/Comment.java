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
public class Comment extends SnwObject {
    private String content;

    @Relationship(type = "BELONG_POST")
    private Post post;
    @Relationship(type = "BELONG_USER")
    private User user;
    @Relationship(type = "HAS_REACTION")
    private Set<Reaction> reactions;
}
