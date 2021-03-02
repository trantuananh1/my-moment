package com.hunganh.mymoment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.NonNull;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Data
@AllArgsConstructor
@Node
public class Account {

    @Id
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String salted_password;
    private Account() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    @Relationship(type = "FOLLOWS")
    private Set<Account> follows_account = new HashSet<>();
    @Relationship(type = "FOLLOWEDBY")
    private Set<Account> followed_by_accountr= new HashSet<>();
}
