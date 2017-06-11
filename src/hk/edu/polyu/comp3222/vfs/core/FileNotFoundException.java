package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;

/**
 * Created by michael on 2017/4/5.
 */
public class FileNotFoundException extends VFSException {
    public FileNotFoundException(String info, String file){
        super(info, file);
    }
}
