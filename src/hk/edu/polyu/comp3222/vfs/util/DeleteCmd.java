package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class DeleteCmd implements Command{
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        String name = null;
        if (cmd.size() > 1 && !cmd.get(1).equals(null)) {
            name = cmd.get(1);
        } else {
            ioService.printLine("Wrong Argument for delete command");
        }
        ioService.printLine("This is the rename command");
        disk.delete(name);
        return disk.getCurrentPath();
    }
}
