package com.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/17 8:48
 * @description TODO
 */
public class Server {
    private String msg;

    public Server() {
    }

    public Server(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Server{" +
                "msg='" + msg + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[20];
        int len;
        while ( (len = is.read(b)) != -1){
            String str = new String(b, 0, len);
            System.out.println(str);
        }
        is.close();
        socket.close();
        server.close();
    }
}
