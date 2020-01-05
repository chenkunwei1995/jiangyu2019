package com.xzj.chapter01.controller.upload;

import com.xzj.chap1.model.basemodel.FileBase;
import com.xzj.helper.file.txt.TxtFileOperation;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    FileBase fileBase;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/");
    SimpleDateFormat txtpathsdf=new SimpleDateFormat("yyyy/MM/");
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req){
        String relpath=fileBase.getImgfile();//req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format=sdf.format(new Date());
        File folder=new File(relpath+format);
        if(!folder.isDirectory()){
            if(!folder.getParentFile().exists())
                folder.getParentFile().mkdirs();
            folder.mkdir();
        }
        String oldName=uploadFile.getOriginalFilename();
        Logger.getLogger(FileUploadController.class.getName()).info("旧的文件名字"+oldName);
        String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try{
            uploadFile.transferTo(new File(folder,newName));
            String filePath=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/uploadFile/imgfile/"+format+newName;
            String txtPath= fileBase.getTxtfile()+txtpathsdf.format(new Date()); // req.getSession().getServletContext().getRealPath("/txt2019/");
            File txtfolder=new File(txtPath);
            if(!txtfolder.isDirectory()){
               if(!txtfolder.getParentFile().exists()){
                   txtfolder.getParentFile().mkdirs();
               }
                txtfolder.mkdir();
            }
            txtPath+=dateFormat.format(new Date())+".txt";
            TxtFileOperation.writeFile(txtPath,filePath);
            return filePath;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "上传失败";

    }
}
