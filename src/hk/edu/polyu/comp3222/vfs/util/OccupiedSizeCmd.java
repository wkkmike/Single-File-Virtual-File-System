package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by lidawei on 06/04/2017.
 */
public class OccupiedSizeCmd implements Command{
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        long size = disk.getSize();
        long remain = disk.getRemainSize();
        long result = size - remain;
        ioService.printLine(String.valueOf(result));
        return disk.getCurrentPath();
    }
}
