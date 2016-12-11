package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    protected int serverPort = 8080;
    private String address = "127.0.0.1";
    protected Socket clientSocket = null;


    public Client(int serverPort) {
        this.serverPort = serverPort;
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            clientSocket = new Socket(ipAddress, serverPort);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void sendMail(String msg) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        writer.println(msg);
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String recvMail() {
        try {
            BufferedReader buffread = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msg;
            System.out.println("HERE");
            if ((msg = buffread.readLine()) != null) {
                return msg;
            }
            System.out.println("NOT HERE");
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
        return null;
    }

}
