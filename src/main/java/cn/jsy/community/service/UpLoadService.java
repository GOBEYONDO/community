package cn.jsy.community.service;

import cn.jsy.community.model.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UpLoadService {
    public FileEntity upLoad(String path, MultipartFile multipartFile);
}
