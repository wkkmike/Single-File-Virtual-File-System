package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by michael on 2017/3/31.
 */
public class VFSFile implements VFSUnit {
    private Date lastMotify;
    private byte[] content;
    private long size;
    private VFSUnit parent;
    private String name;

    public VFSFile(String name, VFSUnit parent, Date createDate, byte[] content){
        this.name = name;
        this.parent = parent;
        this.lastMotify = createDate;
        this.content = content;
        this.size = content.length;
        this.parent.changeSize(this.size);
    }

    public boolean rename(String newName){
        this.name = newName;
        return true;
    }

    @Override
    public boolean delete() {
        long size = this.size;
        this.content = null;
        this.changeSize(-size);
        return true;
    }


    public boolean move(VFSUnit child, Directory path) {
        return true;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Date getLastModify() {
        return lastMotify;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean export(File name) throws IOException{
        if(!name.exists())
            return false;
        File outFile = new File(name.toString() + '/' + this.getName());
        if(!outFile.createNewFile()){
            return false;
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new ByteArrayInputStream(this.content);
            output = new FileOutputStream(outFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
        finally {
            input.close();
            output.close();
        }
        return true;
    }

    public boolean copy(Directory file){
        if(file.haveChild(name)){
            return false;
        }
        VFSUnit out = new VFSFile(this.getName(), file, this.lastMotify, this.content);
        file.addChild(out);
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isVFSFile() {
        return true;
    }

    public String toString(){
        String type = "f";
        String size = String.valueOf(this.size);
        String date = this.lastMotify.toString();
        String name = this.getName();
        String out;
        out = String.format("%s %15s %s %10sbyte",type, name, date, size);
        return out;
    }

    @Override
    public boolean changeLastModify() {
        this.lastMotify = new Date();
        this.parent.changeLastModify();
        return true;
    }

    @Override
    public boolean changeSize(long size) {
        if(this.parent == null) return true;
        this.parent.changeSize(size);
        return true;
    }

    @Override
    public VFSUnit getParent() {
        return this.parent;
    }

    @Override
    public VFSUnit getChild(String name) {
        return null;
    }

    @Override
    public Map<String, VFSUnit> getChildren() {
        return null;
    }

    @Override
    public boolean addChild(VFSUnit child) {
        return false;
    }

    @Override
    public boolean deleteChild(String child) {
        return false;
    }

    @Override
    public boolean importFile(File file) throws IOException {
        return false;
    }

    public boolean haveChild(String name){
        return false;
    }

    @Override
    public List<VFSUnit> search(String[] key, boolean caseSensitive) {
        List<VFSUnit> out = new LinkedList<>();
        if(!caseSensitive){
            String newName = this.name.toUpperCase();
            for(String keyword: key){
                if(!newName.contains(keyword.toUpperCase())) return null;
            }
            out.add(this);
        }
        else{
            for(String keyword: key){
                if(!this.name.contains(keyword)) return null;
            }
            out.add(this);
        }
        return out;
    }

    @Override
    public List<VFSUnit> searchFile(String[] key, boolean caseSensitive) {
        return search(key, caseSensitive);
    }

    @Override
    public List<VFSUnit> searchDirectory(String[] key, boolean caseSensitive) {
        return null;
    }

    @Override
    public boolean changeChildName(String originName, String newName) {
        return false;
    }

    @Override
    public boolean changeParent(VFSUnit parent) {
        this.parent = parent;
        return true;
    }

    @Override
    public String getDisplayName() {
        String out = this.parent.getDisplayName() + "/" + this.getName();
        return out;
    }
}
