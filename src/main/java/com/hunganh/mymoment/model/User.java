package com.hunganh.mymoment.model;

import com.hunganh.mymoment.model.assoc.VerificationOwnership;
import com.sn.appbase.model.SnwObject;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class User extends SnwObject {
    private String username;
    private String saltedPassword;
    private String lastIp;
    private boolean enabled;
    @Relationship(type="HAS_PROFILE")
    private Profile profile;
    @Relationship(type = "FOLLOWS")
    private Set<User> followings;
    @Relationship(type = "FOLLOWED_BY")
    private Set<User> followers;
    @Relationship(type = "HAS_POST")
    private Set<Post> posts;
    @Relationship(type = "HAS_COMMENT")
    private Set<Comment> comments;
    @Relationship(type = "HAS_VERIFICATION_TOKEN")
    private Set<VerificationOwnership> verificationOwnerships;
}
