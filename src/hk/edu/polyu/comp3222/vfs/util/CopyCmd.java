package hk.edu.polyu.comp3222.vfs.util;


import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class CopyCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the copy command");
        String dirName = null;
        String name = null;
        boolean result = false;
        if (cmd.size() > 2 && !cmd.get(1).equals(null) && !cmd.get(2).equals(null) ) {
            name = cmd.get(1);
            dirName = cmd.get(2);
        }
        else {
            ioService.printLine("Wrong Argument for copy command");
        }
        result = disk.copy(name, dirName);
        if(result == false){
            ioService.printLine("cp failed");
        }

        return disk.getCurrentPath();
    }

}
