package com.hunganh.mymoment.base;

import com.hunganh.mymoment.constant.InputParam;
import com.hunganh.mymoment.model.object.Post;
import com.sn.appbase.constant.SnwAssocType;
import com.sn.appbase.constant.SnwObjectType;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.cypher.internal.compiler.v2_0.functions.Str;

import java.util.*;

/**
 * @Author: Tran Tuan Anh
 * @Created: Thu, 08/04/2021 11:01 AM
 **/

@Slf4j
public class PostBase implements ObjectBase{
    public static PostBase getInstance = new PostBase();
    private PostBase(){}

    @Override
    public void addObject(JSONObject jsonData, List<String> offlineIds) {
        JSONObject joPost = jsonData.getJSONObject(SnwObjectType.POST.getName());
        for (String offlineId:offlineIds){
            JSONObject jsonObject = joPost.getJSONObject(offlineId).getJSONObject(InputParam.DATA);
            String caption = jsonObject.getString("caption");
            Post post = Post.builder()
                        .caption(caption)
                        .build();


        }
    }

    @Override
    public void addAssoc(JSONObject jsonData, List<String> offlineIds) {

    }

    @Override
    public void addExtendData(JSONObject jsonData, List<String> offlineIds) {

    }

    @Override
    public Map<String, Object> getInsertData(String data, String userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemIds = jsonObject
                    .getJSONObject(SnwAssocType.HAS_POST.getName())
                    .getJSONObject(userId)
                    .getJSONArray(InputParam.ITEM_IDS);
            String offlineId = itemIds.getString(0);
            JSONObject items = jsonObject.getJSONObject(SnwObjectType.POST.getName());
            JSONObject metaData = items.getJSONObject(offlineId).getJSONObject(InputParam.DATA);
            long createDate = new Date().getTime();
            Post post = Post.builder()
                    .id(UUID.randomUUID().toString())
                    .caption(metaData.getString(InputParam.CAPTION))
                    .build();
            result.put(offlineId, post);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
