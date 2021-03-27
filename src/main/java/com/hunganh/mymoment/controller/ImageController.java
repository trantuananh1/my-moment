package com.hunganh.mymoment.controller;

import com.hunganh.mymoment.dto.UploadFileResponse;
import com.hunganh.mymoment.model.ImageMetadata;
import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.service.AuthService;
import com.hunganh.mymoment.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 22/03/2021 1:40 AM
 **/
@Slf4j
@RestController
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final AuthService authService;

    @PostMapping("/images")
    public UploadFileResponse uploadFile(@RequestParam("image") MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        User currentUser=authService.getCurrentUser();
        log.info("received a request to upload file {} for user {}", filename, currentUser.getUsername());

        ImageMetadata metadata = imageService.upload(file, currentUser.getUsername());

        return new UploadFileResponse(metadata.getFilename(), metadata.getUri(),
                metadata.getFileType());
    }
}
