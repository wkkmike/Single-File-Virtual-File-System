package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.*;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class TouchCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        //ioService.printLine("This is the touch handler.");
        String name = null;
        String option = null;
        boolean result = false;
        if (cmd.size() <= 2) {
            ioService.printLine("touch need two argument");

        } else {
            option = cmd.get(1);
            name = cmd.get(2);
        }
        if(option.equals("file")){
            result = disk.createFile(name,null);
        }
        if(option.equals("dir")){
            result = disk.createDirectory(name);
        }

        if(result == false){
            ioService.printLine("Touch failed");
        }

        return disk.getCurrentPath();
    }
}
