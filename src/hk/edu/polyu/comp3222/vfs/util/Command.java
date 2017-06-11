package hk.edu.polyu.comp3222.vfs.util;


import hk.edu.polyu.comp3222.vfs.core.Directory;
import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

/**
 * Created by DAWEI on 19/3/17.
 *
 */
public interface Command {
    String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk);
}


