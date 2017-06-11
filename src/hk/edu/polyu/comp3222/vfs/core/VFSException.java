package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.io.IOException;

/**
 * Created by michael on 2017/4/2.
 */
public abstract class VFSException extends IOException{
    String info;
    String name;

    public VFSException(String info,String file){
        this.info = info;
        this.name = file;
    }

    public String getInfo(){
      return info;
    };

    public String getFile(){
        return name;
    }
}
