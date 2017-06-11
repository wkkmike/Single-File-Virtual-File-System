package hk.edu.polyu.comp3222.vfs.server;


import hk.edu.polyu.comp3222.vfs.client.Client;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;
import hk.edu.polyu.comp3222.vfs.util.CommandReciever;
import hk.edu.polyu.comp3222.vfs.util.ConsoleIO;
import hk.edu.polyu.comp3222.vfs.util.IOService;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by lidawei on 03/04/2017.
 */
public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private String pw;
    private String choice;
    private HashMap<String, String> accInfo;
    private String command;
    private VirtualDisk disk;

    private Server() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public void listenAcc() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Could not listen on port 8888");
            System.exit(-1);
        }

        try{
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }

        try{
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        try{
            name = in.readLine();
            pw = in.readLine();
            choice = in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        if(choice.equals("l")){
            if(accInfo.get(name).equals(pw)){
                out.println("s");
            }
            else out.println("w");
        }
        else if(choice.equals("s")){
            accInfo.put(name,pw);
        }
        else out.println("n");

        try {
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println("close failed");
            System.exit(-1);
        }
    }

    public void listenCommand() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Could not listen on port 8888");
            System.exit(-1);
        }

        try {
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }

        try {
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Read from client failed");
            System.exit(-1);
        }

        while (true) {
            IOService output = new ConsoleIO();
            try {
                command = in.readLine();
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
            CommandReciever server = new CommandReciever();
            StringTokenizer st = new StringTokenizer(command, " ");
            ArrayList<String> cmd = new ArrayList<String>();
            int count = 0;
            while (st.hasMoreTokens()) {
                cmd.add(st.nextToken());
            }
            String cmd1 = "foo";
            if(cmd.size() > 0){
                cmd1 = cmd.get(0);
            }
            server.execute(cmd1, cmd, output, disk);
        }
    }

}
