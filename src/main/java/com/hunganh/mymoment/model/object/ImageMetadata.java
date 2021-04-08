package com.hunganh.mymoment.model.object;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import javax.persistence.Entity;
import javax.persistence.GenerationType;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 22/03/2021 1:07 AM
 **/

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Node
public class ImageMetadata {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @CreatedBy
    private String username;

    @CreatedDate
    private long createdAt;

    @NonNull
    private String filename;

    @NonNull
    private String uri;

    @NonNull
    private String fileType;
}
