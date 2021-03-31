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
public class Hashtag extends SnwObject {
    private String content;

    @Relationship(type = "HAS_POST")
    private Set<Post> posts;
}
