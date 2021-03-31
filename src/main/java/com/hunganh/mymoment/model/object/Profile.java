package com.hunganh.mymoment.model.object;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Profile {
    @Id
    private String userId;
    private String email;
    private String fullName;
    private String city;
    private String country;
    private String biography;
    private String gender;
    private String dateOfBirth;
    private String avatarUrl;
    private String coverUrl;
}
