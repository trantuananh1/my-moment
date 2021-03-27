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
public class Hashtag extends SnwObject {
    private String content;

    @Relationship(type = "HAS_POST")
    private Set<Post> posts;
}
