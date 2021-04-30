package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int PORT = 19000;
    public static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        boolean b = false;
        try {
            socket = new Socket(HOST, PORT);
                try (InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {

                    while (b = true) {
                        Scanner inn = new Scanner(System.in);
                    System.out.print("Input a string: ");

                    String line = inn.nextLine();
                    out.write(line.getBytes());
                    out.flush();

                    byte[] data = new byte[32 * 1024];
                    int readBytes = in.read(data);
                    String line1 = new String(data, 0, readBytes);
                    System.out.println("Server: " + line1);
                    if (line1.equals("ali")){b = true;}

                }
                }
                catch (IOException e){ e.printStackTrace(); }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
