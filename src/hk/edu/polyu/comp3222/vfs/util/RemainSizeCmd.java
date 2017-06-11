package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class RemainSizeCmd implements Command{
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        long result;
        result = disk.getRemainSize();
        ioService.printLine(String.valueOf(result));
        return disk.getCurrentPath();
    }
}
