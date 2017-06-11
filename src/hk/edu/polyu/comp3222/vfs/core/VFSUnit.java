package hk.edu.polyu.comp3222.vfs.core;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by michael on 2017/3/31.
 */
public interface VFSUnit extends Serializable {
    boolean rename(String name);

    boolean delete();

    boolean move(VFSUnit child, Directory path);

    long getSize();

    Date getLastModify();

    String getName();

    boolean export(File name) throws IOException;

    boolean copy(Directory name);

    boolean isDirectory();

    boolean isVFSFile();

    String toString();

    boolean changeLastModify();

    boolean changeSize(long size);

    VFSUnit getParent();

    VFSUnit getChild(String name);

    Map<String, VFSUnit> getChildren();

    boolean addChild(VFSUnit child);

    boolean deleteChild(String child);

    boolean importFile(File file) throws IOException;

    String getDisplayName();

    boolean haveChild(String name);

    List<VFSUnit> search(String key[], boolean caseSensitive);

    List<VFSUnit> searchFile(String key[], boolean caseSensitive);

    List<VFSUnit> searchDirectory(String key[], boolean caseSensitive);

    boolean changeChildName(String originName, String newName);

    boolean changeParent(VFSUnit parent);
}
