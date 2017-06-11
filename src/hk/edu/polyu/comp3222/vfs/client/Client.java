package hk.edu.polyu.comp3222.vfs.client;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 04/04/2017.
 */
public abstract class Client {
    VirtualDisk disk = null;
    public Client(){}
    public void setDisk(VirtualDisk disk){
        this.disk = disk;
    }
    public void action(){}

}
