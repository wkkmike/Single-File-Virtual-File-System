package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.client.ClientOn;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 06/04/2017.
 */
public class ConnectCmd {
    ClientOn online = new ClientOn();
    public void command(IOService ioService, VirtualDisk disk){
        while(true) {
            ioService.printLine("please enter the server ip");
            String ip = ioService.readLine();
            ioService.printLine("Please enter the server port");
            String port = ioService.readLine();
            int portint = Integer.parseInt(port);
            online.setDisk(disk);
            boolean connect = online.connect(ip, portint);
            if (connect == true) {
                ioService.printLine("Connected");
                break;
            } else ioService.printLine("connect failed");
        }
        ioService.printLine("login(l) or signup(s)?");
        String choice = ioService.readLine();
        ioService.printLine("please provide username, if don't have, create one");
        String name = ioService.readLine();
        ioService.printLine("please provide password");
        String pw = ioService.readLine();
        online.AccInfo(name, pw, choice);
        switch (online.re){
            case "s":
                online.action();
                break;
            case "w":
                ioService.printLine("Wong account information");
                break;
            case "n":
                ioService.printLine("Wrong command");
                break;
        }

    }
}
