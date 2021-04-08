package com.hunganh.mymoment.model.object;

import com.sn.appbase.model.SnwObject;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 05/04/2021 6:17 AM
 **/

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Attachment extends SnwObject {
    private String userId;
    private String url;
    private String name;
    private String mime;
    private int size;
}
