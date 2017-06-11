package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lidawei on 03/04/2017.
 */
public class SearchCmd implements Command {
    public String command(ArrayList<String> cmd, IOService ioService, VirtualDisk disk){
        if(cmd.size() < 3){
            ioService.printLine("search command needs at least three argument");
        }else{
            String option1 = cmd.get(1);
            String option2 = cmd.get(2);
            String path = cmd.get(3);
            boolean caseSensitive;
            if(option2.equals("on")) {
                caseSensitive = true;
            }else caseSensitive = false;
            ArrayList<String> key = null;
            for(int i = 4; i < cmd.size(); i++) {
                key.add(cmd.get(i));
            }
            String[] keyword = new String[key.size()];
            for(int j = 0; j < key.size(); j++) {
                keyword[j] = key.get(j);
            }
            List<VFSUnit> result = null;
            switch (option1){
                case "-file":
                    result = disk.searchFile(path, keyword, caseSensitive);
                    break;
                case "-folder":
                    result = disk.searchDirectory(path,keyword, caseSensitive);
                    break;
                case "-both":
                    result = disk.search(path, keyword, caseSensitive);
                    break;
            }
            if(result.isEmpty()){
                ioService.printLine("no such file or folder");
            }
            else{
                Iterator<VFSUnit> iterator = result.iterator();
                if(iterator.hasNext()){
                    ioService.printLine(iterator.next().getName());
                }
            }

        }
        return disk.getCurrentPath();
    }
}
