package com.gupao.netty1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 服务端
 */
public class Server {

    private static final int SERVER_PORT = 7777;

    public static List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        while (true){
            Socket socket = serverSocket.accept();
            sockets.add(socket);
            new Thread(new ServerHandler(socket)).start();
        }

    }


}
