package com.hunganh.mymoment.base;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface ObjectBase {
    public void addObject(JSONObject jsonData, List<String> offlineIds);
    public void addAssoc(JSONObject jsonData, List<String> offlineIds);
    public void addExtendData(JSONObject jsonData, List<String> offlineIds);
    public Map<String, Object> getInsertData(String data, String userId);
}
