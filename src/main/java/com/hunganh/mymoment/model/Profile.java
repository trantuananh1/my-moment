package com.hunganh.mymoment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Profile {
    private long userId;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String biography;
    private String gender;
    private String dateOfBirth;
    private long dateCreated;
    private long dateUpdated;
    private String lastIp;
}
