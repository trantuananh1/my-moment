package com.hunganh.mymoment.response;

import lombok.Data;
import org.json.JSONObject;

/**
 * @Author: Tran Tuan Anh
 * @Created: Wed, 31/03/2021 12:16 PM
 **/

@Data
public class SnwSuccessResponse {
    private boolean success;
   public SnwSuccessResponse(){
       this.success=true;
   }
}
