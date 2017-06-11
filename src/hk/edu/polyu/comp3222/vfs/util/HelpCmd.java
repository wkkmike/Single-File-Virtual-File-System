package hk.edu.polyu.comp3222.vfs.util;


import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class HelpCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
            //CurrentDir.getPath();
            ioService.printLine("This is help");
            return disk.getCurrentPath();
    }
}
