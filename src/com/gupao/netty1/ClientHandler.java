package com.gupao.netty1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            //获取服务端发送的消息
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMsg;
            while ((serverMsg = br.readLine()) != null){
                System.out.println("客户端收到消息<<<<<<：" + serverMsg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
