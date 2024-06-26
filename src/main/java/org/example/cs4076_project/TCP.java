package org.example.cs4076_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP {
    Socket link = null;
    BufferedReader in;
    PrintWriter out;

    public String init() {
        InetAddress IP;
        try {
            IP = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e) {
            return "IP of local machine not found!";
        }

        try {
            int PORT = 9999;
            link = new Socket(IP, PORT);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            out = new PrintWriter(link.getOutputStream(),true);
        }
        catch(IOException e) {
            return "Unable to establish socket to server!";
        }
        return "OK";
    }

    public String send(String message) {
        String response = "";
        out.println(message);
        try {
            response = in.readLine();
        } catch (IOException e) {
            return "ERR: Response from server was not received!";
        }
        return response;
    }
}
