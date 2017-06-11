package hk.edu.polyu.comp3222.vfs.client;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import hk.edu.polyu.comp3222.vfs.Executer;
import hk.edu.polyu.comp3222.vfs.core.*;
import hk.edu.polyu.comp3222.vfs.util.CommandReciever;
import hk.edu.polyu.comp3222.vfs.util.ConsoleIO;
import hk.edu.polyu.comp3222.vfs.util.IOService;

/**
 * Created by lidawei on 02/04/2017.
 */
public class ClientOn extends Client{
    private String account;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public String re;

    public ClientOn(){
        super();
    }

    public boolean connect(String ip, int port){
        try{
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(),
                    true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(1);
            return false;
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
            return false;
        }
        return true;
    }

    public void AccInfo(String acc, String pw, String choice){
        out.println(acc);
        out.println(pw);
        out.println(choice);
        try{
            re = in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }

    public void command(ArrayList<String> command){
        out.println(command);
    }

    @Override
    public void action(){
        IOService output = new ConsoleIO();
        while(true) {
            output.printLine(disk.getCurrentPath() + "> ");
            String input = output.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");
            ArrayList<String> cmd = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                cmd.add(st.nextToken());
            }
            String cmd1 = "foo";
            if(cmd.size() > 0){
                cmd1 = cmd.get(0);
            }
            CommandReciever receiver = new CommandReciever();
            this.command(cmd);
            receiver.execute(cmd1, cmd, output, disk);
        }
    }
}


