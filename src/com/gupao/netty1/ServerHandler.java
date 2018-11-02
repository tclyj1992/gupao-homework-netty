package com.gupao.netty1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            //接受消息
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content;
            while ((content = br.readLine()) != null){
                System.out.println("服务端接收到消息<<<<<：" + content);
                //发送给所有客户端
                for (Socket socket1 : Server.sockets) {
                    PrintWriter printWriter = new PrintWriter(socket1.getOutputStream());
                    printWriter.println(content);
                    printWriter.flush();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
