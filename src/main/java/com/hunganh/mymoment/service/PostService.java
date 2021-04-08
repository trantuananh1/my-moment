package com.hunganh.mymoment.service;

import com.sn.appbase.constant.SnwAssocType;
import com.sn.appbase.constant.SnwObjectType;
import com.sn.appbase.model.SnwObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Tran Tuan Anh
 * @Created: Wed, 07/04/2021 11:30 PM
 **/

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    public Map<String, Object> addPost(String data, String userId) {
        Map<String, Object> result = new HashMap<>();
        JSONObject joData = new JSONObject(data);
        JSONObject joHasPost = joData.getJSONObject(SnwAssocType.HAS_POST.getName());
        JSONArray jaItemIds = joHasPost.getJSONArray(userId);
        List<String> offlineIds = new ArrayList<>();
        for (int i = 0; i < jaItemIds.length(); i++) {
            offlineIds.add(jaItemIds.getString(i));
        }
        JSONObject joPost = joData.getJSONObject(SnwObjectType.POST.getName());
        for (String offlineId:offlineIds){
        }
        return result;
    }
}
