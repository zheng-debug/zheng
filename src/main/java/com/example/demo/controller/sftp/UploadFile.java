package com.example.demo.controller.sftp;

import com.example.demo.entity.SftpAuthority;
import com.example.demo.service.sftp.UploadService;
import com.jcraft.jsch.JSchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/upload")
public class UploadFile {
    @Autowired
    private UploadService uploadService;


    @RequestMapping("upload")
    public String toUploadPage(HttpServletRequest request) throws JSchException {

        SftpAuthority sftpAuthority = new SftpAuthority("root","120.79.95.178",22);
        sftpAuthority.setPassword("password");
        uploadService.createChanner(sftpAuthority);
        uploadService.uploadFile(sftpAuthority,"","");
        uploadService.closeChannel();

        return "/html/upload";
    }

}
