package hk.edu.polyu.comp3222.vfs.test;

import hk.edu.polyu.comp3222.vfs.core.Util.ConsoleIO;
import hk.edu.polyu.comp3222.vfs.core.Util.IOService;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSDirectory;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSFile;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSunit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by michael on 2017/4/5.
 */
public class VFSDirectoryTest {
    private VFSDirectory testDirectory1;
    private VFSDirectory testDirectory2;
    private VFSDirectory testDirectory3;
    private VFSFile testFile1;
    private VFSFile testFile2;
    private IOService io;
    @Before
    public void setUp() throws Exception {
        testDirectory1 = new VFSDirectory("root/test/", "testDir1", new Date(1l));
        testDirectory2 = new VFSDirectory("root/test/", "testDir2", new Date(1l));
        testDirectory3 = new VFSDirectory("root/test/testDir1", "testDir3", new Date(1l));
        testFile1 = new VFSFile("root/test/testDir1/", "testFile1", new Date(1l), null);
        testFile2 = new VFSFile("root/test/testDir1/", "testFile2", new Date(1l), null);
        testDirectory1.getDirContent().put("testFile1", testFile1);
        testDirectory1.getDirContent().put("testFile2", testFile2);
        testDirectory1.getDirContent().put("testDir3", testDirectory3);
        io = new ConsoleIO();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1(){
        testDirectory1.list(true, true, io);
        testDirectory1.list(true, false, io);
        testDirectory1.list(false, true, io);
        testDirectory1.list(false, false, io);
        testDirectory2.list(true, true, io);
        testDirectory2.list(true, false, io);
        testDirectory2.list(false, true, io);
        testDirectory2.list(false, false, io);
        assertFalse(testDirectory1.equals(null));
        assertTrue(testDirectory1.equals(testDirectory1));
        assertFalse(testDirectory1.equals(testDirectory3));
        VFSunit equalDir = new VFSDirectory("root/test/", "testDir2", new Date(1l));
        assertFalse(testDirectory2.equals(equalDir));
        assertFalse(testDirectory1.equals(testFile1));
        String[] keyWord = {"testFile1", "testFiel2"};
        assertNull(testDirectory2.getItem(keyWord, null));

    }
}