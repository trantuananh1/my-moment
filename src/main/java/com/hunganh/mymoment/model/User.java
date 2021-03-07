package com.hunganh.mymoment.model;

import com.hunganh.mymoment.dto.SignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import javax.persistence.UniqueConstraint;
import java.util.Random;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class User {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String username;
    private String saltedPassword;
    @CreatedDate
    private long dateCreated;
    @LastModifiedDate
    private long dateUpdated;
    private String lastIp;
    private boolean enabled;

    @Relationship(type = "FOLLOWS")
    private Set<User> followings;
    @Relationship(type = "FOLLOWED_BY")
    private Set<User> followers;
    @Relationship(type = "HAS_PROFILE")
    private Profile profile;
    @Relationship(type = "HAS_POST")
    private Set<Post> posts;
    @Relationship(type = "HAS_COMMENT")
    private Set<Comment> comments;

    public void hasProfile(Profile profile) {
        if (profile == null) {
            profile = new Profile();
        }
        this.profile=profile;
    }
}
