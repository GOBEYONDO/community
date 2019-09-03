package cn.jsy.community.controller;

import cn.jsy.community.model.entity.FileEntity;
import cn.jsy.community.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileUploadController {
    @Autowired
    private UpLoadService upLoadService;
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileEntity upLoad(@RequestParam("editormd-image-file")MultipartFile multipartFile,
                             HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("static/images/");
        FileEntity fileEntity = upLoadService.upLoad("E:\\ideajava\\community\\src\\main\\resources\\static\\images", multipartFile);
        return fileEntity;
    }
}
