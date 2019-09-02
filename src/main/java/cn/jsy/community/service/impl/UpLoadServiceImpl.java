package cn.jsy.community.service.impl;

import cn.jsy.community.entity.FileEntity;
import cn.jsy.community.service.UpLoadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UpLoadServiceImpl implements UpLoadService {

    @Override
    public FileEntity upLoad(String path,MultipartFile multipartFile) {
        FileEntity fileEntity =new FileEntity();
        try {
            String filename = multipartFile.getName();
            System.out.println(filename);
            String uuid=UUID.randomUUID().toString();
            filename=uuid+filename;
            File file =new File(path);
            System.out.println(file.getName());
            if(!file.exists()){
                file.mkdirs();
            }
            multipartFile.transferTo(new File(path,filename));
            fileEntity.setSuccess(1);
            fileEntity.setMessage("上传成功");
            fileEntity.setUrl("http://localhost:8080/images/"+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileEntity;
    }
}
