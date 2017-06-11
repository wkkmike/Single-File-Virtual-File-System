package hk.edu.polyu.comp3222.vfs.client;

import hk.edu.polyu.comp3222.vfs.Executer;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;
import hk.edu.polyu.comp3222.vfs.util.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lidawei on 04/04/2017.
 */
public class ClientOff extends Client {
    public ClientOff() {
        super();
    }


    @Override
    public void action() {
        IOService output = new ConsoleIO();
        while (true) {
            output.printLine("link or create a disk");
            String choice = output.readLine();
            if (choice.equals("link")) {
                output.printLine("disk path please: ");
                String path = output.readLine();
                try {
                    FileInputStream fileIn = new FileInputStream(path);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    VirtualDisk disk = (VirtualDisk) in.readObject();
                    this.setDisk(disk);
                    in.close();
                    fileIn.close();
                    break;
                } catch (IOException i) {
                    i.printStackTrace();
                    break;
                } catch (ClassNotFoundException c) {
                    System.out.println("disk not found");
                    c.printStackTrace();
                    break;
                }
            } else if (choice.equals("create")) {
                output.printLine("please enter disk name: ");
                String name = output.readLine();
                output.printLine("please enter disk size: ");
                long size = Long.parseLong(output.readLine());
                output.printLine("please enter a folder path: ");
                String path = output.readLine();
                VirtualDisk disk = new VirtualDisk(name, size, path);
                this.setDisk(disk);
                break;
            }
        }
        while (true) {
            output.printLine(disk.getCurrentPath() + "> ");
            String input = output.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");
            ArrayList<String> cmd = new ArrayList<String>();
            int count = 0;
            while (st.hasMoreTokens()) {
                cmd.add(st.nextToken());
            }
            String cmd1 = "foo";
            if(cmd.size() > 0){
                cmd1 = cmd.get(0);
            }
            CommandReciever receiver = new CommandReciever();
            receiver.execute(cmd1, cmd, output, disk);

        }
    }
}
