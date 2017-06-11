package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class StoreCmd implements Command{
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        boolean result = false;
        result = disk.store();
        if(result == false){
            ioService.printLine("store failed");
        }
        return disk.getCurrentPath();
    }
}
