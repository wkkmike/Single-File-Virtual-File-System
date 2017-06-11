package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 2017/4/2.
 */
public class PathParser implements Parser{
    private String path;

    public PathParser(String command, VFSUnit currentPath){
        if(command.charAt(0) == '.'){
            path = currentPath.getDisplayName() + command.substring(1);
        }
        else{
            path = command;
        }
        System.out.println(path);
    }

    public String[] getElement(){
        return path.split("/");
    }

    public String get(){
        return path;
    }
}
