package hk.edu.polyu.comp3222.vfs.util;


import hk.edu.polyu.comp3222.vfs.core.*;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class MkdirCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the mkdir handler.");
        String dirName = null;
        boolean result = false;
        if (cmd.size() > 1 && !cmd.get(1).equals(null)) {
            dirName = cmd.get(1);
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        result = disk.createDirectory(dirName);
        if(result == false){
            ioService.printLine("mkdir failed");
        }

        return disk.getCurrentPath();
    }
}
