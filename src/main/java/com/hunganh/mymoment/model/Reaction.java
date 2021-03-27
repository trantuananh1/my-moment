package com.hunganh.mymoment.model;

import com.sn.appbase.model.SnwObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Reaction extends SnwObject {
    private String userId;
    private int type;
}
