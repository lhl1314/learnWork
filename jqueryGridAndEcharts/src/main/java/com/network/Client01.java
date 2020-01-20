package com.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author lhl
 * @version 1.0
 * @date 2020/1/17 8:48
 * @description TODO
 */
public class Client01 {
    public static void main(String[] args) throws IOException {
            // 创建一个 Socket 对象，指明IP和端口号
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8080);
            OutputStream os = socket.getOutputStream();
            // 发送数据给服务端
            System.out.println("我是客户端01");
            os.write("我是客户端01".getBytes());
            os.close();
            socket.close();
    }
}
