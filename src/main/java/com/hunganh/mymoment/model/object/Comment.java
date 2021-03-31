package com.hunganh.mymoment.model.object;

import com.sn.appbase.model.SnwObject;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@ToString
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
