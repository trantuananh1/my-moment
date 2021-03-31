package com.hunganh.mymoment.response;

import lombok.Data;

/**
 * @Author: Tran Tuan Anh
 * @Created: Wed, 31/03/2021 11:27 PM
 **/

@Data
public class SnwAddResponse {
    private String offlineId;
    private String objectId;
    private long createdTime;

    public SnwAddResponse(String offlineId, String objectId, long createdTime){
        this.offlineId=offlineId;
        this.objectId=objectId;
        this.createdTime=createdTime;
    }
}
