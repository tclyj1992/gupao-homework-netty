package com.gupao.netty1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {

    private static final int SERVER_PORT = 7777;


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",SERVER_PORT);
        new Thread(new ClientHandler(socket)).start();//处理服务端消息
        //获取输入，发送到服务端
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String clientMsg;
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        while ((clientMsg = br.readLine())!=null){  //输入-1结束发送
            System.out.println("客户端发送消息>>>>>："+ clientMsg);
            pw.println(clientMsg);
            pw.flush();
        }

    }


}
