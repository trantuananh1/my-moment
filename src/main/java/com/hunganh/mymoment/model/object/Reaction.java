package com.hunganh.mymoment.model.object;

import com.sn.appbase.model.SnwObject;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Reaction extends SnwObject {
    private String userId;
    private int type;
}
