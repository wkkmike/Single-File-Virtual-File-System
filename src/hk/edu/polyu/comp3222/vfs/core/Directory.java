package hk.edu.polyu.comp3222.vfs.core;

import java.io.*;
import java.util.*;

/**
 * Created by michael on 2017/3/31.
 */
public class Directory implements VFSUnit{
    private Date lastMotify;
    private String name;
    private Map<String, VFSUnit> children = new HashMap<>();
    private VFSUnit parent;
    private long size;

    public Directory(String name, VFSUnit parent, Date createDate){
        this.parent = parent;
        this.name = name;
        this.size = 0;
        this.lastMotify = createDate;
    }

    @Override
    public boolean rename(String name) {
        this.name = name;
        return true;
    }

    @Override
    public boolean delete() {
        for(VFSUnit child: children.values()){
            Boolean flag = child.delete();
            if(!flag) return false;
        }
        this.children = null;
        return true;
    }

    @Override
    public boolean move(VFSUnit child, Directory path) {
        if(path.haveChild(child.getName())){
            return false;
        }
        VFSUnit temp = child;
        String childName = child.getName();
        this.children.remove(childName);
        path.addChild(temp);
        return true;
    }

    @Override
    public long getSize() {
        long size = 0;
        for(VFSUnit file: children.values()){
            size += file.getSize();
        }
        return size;
    }

    public Map<String, VFSUnit> getChildren(){
       return this.children;
    }

    @Override
    public Date getLastModify() {
        return this.lastMotify;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean export(File name) throws IOException{
        if(!name.exists()){
            return false;
        }
        File outFile = new File(name.toString() + '/' + this.getName());
        if(outFile.exists()){
            return false;
        }
        if(!outFile.mkdir()){
            return false;
        }
        for(VFSUnit child: children.values()){
            boolean flag = child.export(outFile);
            if(!flag) return false;
        }
        return true;
    }

    @Override
    public boolean copy(Directory file){
        if(file.haveChild(name)){
            return false;
        }
        Directory out = new Directory(this.name, file, new Date());
        for(VFSUnit f: children.values()){
            f.copy(out);
        }
        file.addChild(this);
        return true;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public boolean importFile(File file) throws IOException{
        if(!file.exists()){
            return false;
        }
        if(haveChild(file.getName())){
            return false;
        }
        if(file.isFile()){
            InputStream input = null;
            ByteArrayOutputStream output = null;
            byte[] out;
            try {
                input = new FileInputStream(file);
                output = new ByteArrayOutputStream();
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
            out = output.toByteArray();
            VFSUnit child = new VFSFile(file.getName(), this, new Date(), out);
            addChild(child);
            return true;
        }
        if(file.isDirectory()){
            VFSUnit child = new Directory(file.getName(), this, new Date());
            addChild(child);
            for(File f: file.listFiles()){
                boolean flag = child.importFile(f);
                if(!flag) return false;
            }
        }
        return true;
    }

    @Override
    public String getDisplayName() {
        if(this.parent == null)
            return this.getName();
        return this.parent.getDisplayName() + "/" + this.getName();
    }

    @Override
    public boolean haveChild(String name) {
        if(children.containsKey(name)) return true;
        return false;
    }

    @Override
    public List<VFSUnit> search(String[] key, boolean caseSensitive) {
        List<VFSUnit> out = new LinkedList<>();
        for(VFSUnit child: children.values()){
            out.addAll(child.search(key, caseSensitive));
        }
        boolean flag = true;
        if(!caseSensitive){
            String newName = this.name.toUpperCase();
            for(String keyword: key){
                if(!newName.contains(keyword.toUpperCase())) flag = false;
            }
        }
        else{
            for(String keyword: key){
                if(!this.name.contains(keyword)) flag = false;
            }
        }
        if(flag) out.add(this);
        return out;
    }

    @Override
    public List<VFSUnit> searchFile(String[] key, boolean caseSensitive) {
        List<VFSUnit> out = new LinkedList<>();
        for(VFSUnit child: children.values()){
            out.addAll(child.searchFile(key, caseSensitive));
        }
        return out;
    }

    @Override
    public List<VFSUnit> searchDirectory(String[] key, boolean caseSensitive) {
        List<VFSUnit> out = new LinkedList<>();
        for(VFSUnit child: children.values()){
            out.addAll(child.searchFile(key, caseSensitive));
        }
        boolean flag = true;
        if(!caseSensitive){
            String newName = this.name.toUpperCase();
            for(String keyword: key){
                if(!newName.contains(keyword.toUpperCase())) flag = false;
            }
        }
        else{
            for(String keyword: key){
                if(!this.name.contains(keyword)) flag = false;
            }
        }
        if(flag) out.add(this);
        return out;
    }

    public boolean addChild(VFSUnit child){
        if(haveChild(child.getName())) return false;
        this.children.put(child.getName(), child);
        child.changeParent(this);
        this.changeSize(child.getSize());
        return true;
    }

    @Override
    public boolean deleteChild(String name){
        for(VFSUnit f: children.values()){
            if(f.getName().equals(name)){
                f.delete();
                children.remove(name);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isVFSFile() {
        return false;
    }

    public boolean changeSize(long size){
        this.size += size;
        if(this.parent == null){
            return true;
        }
        this.size = this.getSize();
        this.parent.changeSize(size);
        return true;
    }

    @Override
    public VFSUnit getParent() {
        return this.parent;
    }

    @Override
    public VFSUnit getChild(String name) {
        for(String f: children.keySet()){
            if(f.equals(name)){
                return children.get(f);
            }
        }
        return null;
    }

    public boolean changeLastModify(){
        this.lastMotify = new Date();
        this.parent.changeLastModify();
        return true;
    }

    public String toString(){
        String type = "d";
        String size = String.valueOf(this.size);
        String date = this.lastMotify.toString();
        String name = this.getName();
        String out;
        out = String.format("%s %15s %s %sbyte",type, name, date, size);
        return out;
    }

    public boolean changeChildName(String originName, String newName){
        VFSUnit child = getChild(originName);
        if(child == null){
            return false;
        }
        if(haveChild(newName)){
            return false;
        }
        child.rename(newName);
        VFSUnit temp = child;
        this.children.remove(originName);
        this.children.put(newName, temp);
        return true;
    }

    @Override
    public boolean changeParent(VFSUnit parent) {
        this.parent = parent;
        return true;
    }
}
