package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.Date;
import java.util.List;
import hk.edu.polyu.comp3222.vfs.util.PathParser;

/**
 * A virtual disk.
 */
public class VirtualDisk implements Serializable{
    private long size;
    private long occupySize;
    private String name;
    private Date lastModify;
    private VFSUnit currentPath;
    private VFSUnit content;
    private String localFile;
    /*
     * Default constructor.
     */

    public VirtualDisk(String name, long size, String file){
        this.name = name;
        this.content = new Directory("root", null, new Date());
        this.currentPath = this.content;
        this.size = size;
        this.occupySize = 0;
        this.lastModify = new Date();
        this.localFile = file + name + ".vfs";
    }
/*
    public VirtualDisk(File path){
        this.ROOT_PATH = path;
        File content = new File(path.toString() + "/" + "root");
        if(!content.exists()){
            content.mkdirs();
        }
        this.content = new Directory(content, null);
        this.currentPath = this.content;
        File info = new File(path.toString() + "/info.txt");
        this.info = info;
        File log = new File(path.toString() + "/log.txt");
        this.log = log;
        try {
            BufferedReader br = new BufferedReader(new FileReader(info));
            String line = br.readLine();
            this.size = Long.parseLong(line);
            line = br.readLine();
            this.occupySize = Long.parseLong(line);
            line = br.readLine();
            this.lastModify = new Date(Long.parseLong(line));
        }
        catch (IOException e){
            System.out.println("Get info failed");
        }
    }
*/
    public long getSize(){
        return size;
    }

    public long getRemainSize(){
        this.occupySize = this.content.getSize();
        return size - occupySize;
    }

    public Date getLastModify(){
        return this.lastModify;
    }

    public boolean dispose(){
        if(!this.content.delete()){
            return false;
        }
        return true;
    }

    public boolean changeDirectory(){
        this.currentPath  = this.content;
        return true;
    }

    public boolean changeDirectory(String path){
        VFSUnit temp = this.getVFSUnitByPath(path);
        if(temp == null) return false;
        currentPath = temp;
        return true;
    }

    public boolean importFile(String fileName){
        File importFile = new File(fileName);
        if(!importFile.exists()){
            return false;
        }
        try {
            this.currentPath.importFile(importFile);
        }
        catch (IOException e){
            System.out.println("import fail");
        }
        this.refresh();
        if(this.getRemainSize() < 0){
            this.currentPath.deleteChild(fileName);
            System.out.println("Don't have enough space");
            return false;
        }
        return true;
    }

    public boolean listChildren(){
        for(VFSUnit f: this.currentPath.getChildren().values()){
            System.out.println(f.toString());
        }
        return true;
    }

    public boolean rename(String originName, String newName){
        return currentPath.changeChildName(originName, newName);
    }

    

    public boolean delete(String name){
        VFSUnit temp = this.currentPath.getChild(name);
        if(temp == null)
            return false;
        if(!temp.delete())
            return false;
        this.refresh();
        return true;
    }

    public boolean createDirectory(String name){
        VFSUnit newDir = new Directory(name, this.currentPath, new Date());
        return this.currentPath.addChild(newDir);
    }

    public boolean createFile(String name, String content){
        VFSUnit newFile = new VFSFile(name,this.currentPath, new Date(), content.getBytes());
        return this.currentPath.addChild(newFile);
    }

    public String getCurrentPath(){
        return this.name + "://" + this.currentPath.getDisplayName();
    }

    private void refresh(){
        this.occupySize = this.content.getSize();
        this.lastModify = this.content.getLastModify();
    }

    public boolean move(String name, String path){
        VFSUnit child = this.currentPath.getChild(name);
        VFSUnit temp = getVFSUnitByPath(path);
        if(temp == null) return false;
        if(temp instanceof VFSFile){
            return false;
        }
        this.currentPath.move(child, (Directory) temp);
        return true;
    }

    public boolean exportFile(String path){
        File export = new File(path);
        if(!export.exists()) return false;
        if(!export.isDirectory()) return false;
        try {
            this.currentPath.export(export);
        }
        catch(IOException e){
            System.out.println("eee");
            return false;
        }
        return true;
    }

    public boolean store(){
        try {
            File outFile = new File(localFile);
            outFile.delete();
            outFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(localFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + localFile + " .\n");
        }catch(IOException i) {
            i.printStackTrace();
        }
        return true;
    }

    public VFSUnit getVFSUnitByPath(String path){
        PathParser parser = new PathParser(path, currentPath);
        String[] element = parser.getElement();
        VFSUnit temp = this.content;
        if(element.length == 0) return temp;
        for(int i=1; i<element.length; i++){
            temp = temp.getChild(element[i]);
            if(temp == null)
                return null;
        }
        if(temp.isVFSFile()) return null;
        return temp;
    }

    public List<VFSUnit> search(String path, String key[], boolean caseSensitive){
        if(path == null) path = this.currentPath.getDisplayName();
        return this.getVFSUnitByPath(path).search(key, caseSensitive);
    }

    public List<VFSUnit> searchFile(String path, String key[], boolean caseSensitive){
        if(path == null) path = this.currentPath.getDisplayName();
        return this.getVFSUnitByPath(path).searchFile(key, caseSensitive);
    }

    public boolean copy(String name, String path){
        VFSUnit copyDes = getVFSUnitByPath(path);
        VFSUnit copyOrigin = this.currentPath.getChild(name);
        if(copyOrigin.getSize() > this.getRemainSize()){
            System.out.println("Don't have enough space");
            return false;
        }
        if(copyDes == null || copyOrigin == null) return false;
        if(copyDes.isVFSFile()) return false;
        if(!copyOrigin.copy((Directory) copyDes)) return false;
        return true;
    }

    public List<VFSUnit> searchDirectory(String path, String key[], boolean caseSensitive){
        if(path == null) path = this.currentPath.getDisplayName();
        if(path.equals("root/")) path = "root/root";
        return this.getVFSUnitByPath(path).searchDirectory(key, caseSensitive);
    }
}
