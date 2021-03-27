package com.hunganh.mymoment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Tran Tuan Anh
 * @Created: Mon, 22/03/2021 1:42 AM
 **/

@Data
@AllArgsConstructor
public class UploadFileResponse {

    private String fileName;
    private String uri;
    private String fileType;
}
