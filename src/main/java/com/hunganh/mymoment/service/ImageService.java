package com.hunganh.mymoment.service;

import com.hunganh.mymoment.model.ImageMetadata;
import com.hunganh.mymoment.repository.ImageMetadataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 22/03/2021 1:27 AM
 **/

@Service
@AllArgsConstructor
@Slf4j
public class ImageService {
    private final FileStorageService fileStorageService;
    private final ImageMetadataRepository imageMetadataRepository;

    public ImageMetadata upload(MultipartFile file, String username) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("storing file {}", filename);

        ImageMetadata metadata = fileStorageService.store(file, username);
        return imageMetadataRepository.save(metadata);
    }
}
