package com.example.demo.service.sftp;

import com.example.demo.entity.SftpAuthority;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class UploadService {
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp;
    private final static Logger logger = LoggerFactory.getLogger(UploadService.class);

    //创建连接
    public void createChanner(SftpAuthority sftpAuthority) throws JSchException {
        try{
            JSch jSch = new JSch();
            session =  jSch.getSession(sftpAuthority.getUsername(),sftpAuthority.getHost(),sftpAuthority.getPort());
            if(sftpAuthority.getPassword()!=null){
                session.setPassword(sftpAuthority.getPassword());
            }
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking","no"); // 主动接收ECDSA key fingerprint，不进行HostKeyChecking
            session.setConfig(properties);
            session.setTimeout(0);
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("create sftp channel failed!",e);
        }
    }

    //关闭连接
    public void closeChannel(){
        if(channel!=null){
            channel.disconnect();
        }
        if(session!=null){
            session.disconnect();
        }
    }

    //上传文件
    public boolean uploadFile(SftpAuthority sftpAuthority,String src,String dst) throws JSchException {
        if(channelSftp == null){
            logger.error("need create channelSftp before upload file");
            return false;
        }
        if(channelSftp.isClosed()){
            createChanner(sftpAuthority);
        }
        try {
            channelSftp.put(src,dst,ChannelSftp.OVERWRITE);
            logger.info("sftp upload file is success! src:"+src+".  dst:"+dst);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
            logger.info("sftp upload file is failed! src:"+src+"  dst:"+dst,e);
            return false;
        }
    }

    //移除文件
    public boolean removeFile(SftpAuthority sftpAuthority,String dst) throws JSchException {
        if (channelSftp == null){
            logger.error("need create channelSftp before remove file");
            return false;
        }
        if(channelSftp.isClosed()){
            createChanner(sftpAuthority);
        }
        try {
            channelSftp.rm(dst);
            logger.info("sftp remove file is success! dst:"+dst);
            return true;
        }catch (SftpException e){
            logger.error("sftp remove file is failed! dst:"+dst);
            return false;
        }
    }
}
