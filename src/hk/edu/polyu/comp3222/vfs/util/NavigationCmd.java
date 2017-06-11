package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.Directory;
import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class NavigationCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        boolean result = false;
        if(cmd.size() == 1){
             disk.changeDirectory();
        }else{
            String path = cmd.get(1);
            result = disk.changeDirectory(path);
            if(result == false){
                ioService.printLine("no such directory");
            }
        }
        return disk.getCurrentPath();

    }
}
