package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class ImportCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the import.");
        String path = null;
        boolean result = false;
        if (cmd.size() > 1 && !cmd.get(1).equals(null)) {
            path = cmd.get(1);
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        result = disk.importFile(path);

        if(result == false){
            ioService.printLine("import failed");
        }

        return disk.getCurrentPath();
    }
}


