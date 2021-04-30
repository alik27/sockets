package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 19000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Started, waiting for connection");
            Socket socket = serverSocket.accept();
            System.out.println("Accepted. " + socket.getInetAddress());
            boolean b = false;
                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {

                    while (b = true) {
                        byte[] data = new byte[32 * 1024];
                        int readBytes = in.read(data);
                        String line = new String(data, 0, readBytes);
                        System.out.println("Client: " + line);

                        out.write(line.getBytes());
                        out.flush();
                        if (line.equals("alik")) {
                            b = true;
                            in.close();
                            out.close();
                            socket.close();
                            System.out.println("Close socket");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
