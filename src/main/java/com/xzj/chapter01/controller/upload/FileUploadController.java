package com.xzj.chapter01.controller.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class FileUploadController {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req){
        String relpath=req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format=sdf.format(new Date());
        File folder=new File(relpath+format);
        if(!folder.isDirectory()){
            folder.mkdir();
        }
        String oldName=uploadFile.getOriginalFilename();
        Logger.getLogger(FileUploadController.class.getName()).info("旧的文件名字"+oldName);
        String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try{
            uploadFile.transferTo(new File(folder,newName));
            String filePath=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/uploadFile/"+format+newName;
            return filePath;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "上传失败";

    }
}
