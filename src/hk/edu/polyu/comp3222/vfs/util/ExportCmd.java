package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class ExportCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the export.");
        String path = null;
        boolean result = false;
        if (cmd.size() > 1 && !cmd.get(1).equals(null)) {
            path = cmd.get(1);
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        result = disk.exportFile(path);

        if(result == false){
            ioService.printLine("export failed");
        }

        return disk.getCurrentPath();
    }
}
