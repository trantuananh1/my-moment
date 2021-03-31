package com.hunganh.mymoment.model.object;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 22/03/2021 1:07 AM
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ImageMetadata {
    @Id
    @GeneratedValue
    private long id;

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
