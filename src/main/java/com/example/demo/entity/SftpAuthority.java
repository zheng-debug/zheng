package com.example.demo.entity;

public class SftpAuthority {
    private String host; //服务器ip或主机名
    private int port; //sftp端口
    private String username;  //登录用户名
    private String password;  //登录密码

    public SftpAuthority() {
    }

    public SftpAuthority(int port, String username, String password) {
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public SftpAuthority( String username, String host,int port) {
        this.port = port;
        this.username = username;
        this.host = host;
    }

    public SftpAuthority(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
