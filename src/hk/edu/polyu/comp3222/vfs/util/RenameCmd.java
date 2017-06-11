package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 03/04/2017.
 */
public class RenameCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        String originName = null;
        String newName = null;
        boolean result = false;
        if (cmd.size() > 1 && !cmd.get(1).equals(null) && !cmd.get(2).equals(null)) {
            originName = cmd.get(1);
            newName = cmd.get(2);
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        ioService.printLine("This is the rename command");
        disk.rename(originName, newName);

        if(result == false){
            ioService.printLine("rename failed");
        }
        return disk.getCurrentPath();
    }
}
