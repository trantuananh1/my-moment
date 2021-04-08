package com.hunganh.mymoment.controller;

import com.hunganh.mymoment.constant.InputParam;
import com.hunganh.mymoment.response.SnwAddResponse;
import com.hunganh.mymoment.response.SnwErrorResponse;
import com.hunganh.mymoment.service.AuthService;
import com.hunganh.mymoment.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/posts", produces = "application/json")
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity addPost(@RequestBody String data) {
        String userId = authService.getCurrentUser().getId();
        Map<String, Object> result = postService.addPost(data, userId);
        if (result==null && result.size()==0){
            return new ResponseEntity(new SnwErrorResponse("can't add"), HttpStatus.BAD_REQUEST);
        }
        String offlineId = (String) result.get(InputParam.OFFLINE_ID);
        String id = (String) result.get(InputParam.ID);
        return new ResponseEntity(new SnwAddResponse(offlineId, id), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public void updatePost(@RequestBody String data, @PathVariable long postId) {
    }

    @GetMapping("/{postId}")
    public void getPost(@PathVariable long postId) {
    }

    @GetMapping("/by_user/{userId}")
    public void getPostByUser(@PathVariable long userId) {
    }

    @GetMapping("/by_tag/{tagId}")
    public void getPostByTag(@PathVariable long tagId) {
    }

    @GetMapping("/by_hashtag/{hashtagId}")
    public void getPostByHashtag(@PathVariable long hashtagId) {
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable long postId) {
    }

    @PostMapping("/hide/{postId}")
    public void hidePost(@PathVariable long postId) {
    }

    @PostMapping("/unhide/{postId}")
    public void unhidePost(@PathVariable long postId) {
    }
}
