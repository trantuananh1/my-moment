package com.hunganh.mymoment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Tran Tuan Anh
 * @Created: Wed, 31/03/2021 7:21 AM
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssocOutput implements Serializable {
    private String objectId;
    private long minScore;
    private long maxScore;
    private int total;
    private List<String> itemIds;
}
