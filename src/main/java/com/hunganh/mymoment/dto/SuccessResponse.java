package com.hunganh.mymoment.dto;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.util.HashMap;

public class SuccessResponse extends HashMap<String, Object> {
    public SuccessResponse(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        this.put("data", jsonObject.toString());
    }
}
