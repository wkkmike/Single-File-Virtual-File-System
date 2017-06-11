package hk.edu.polyu.comp3222.vfs.test;

import hk.edu.polyu.comp3222.vfs.core.Util.IOService;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSFile;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSunit;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by michael on 2017/4/5.
 */
public class VFSFileTest {
    private VFSFile testFile;
    private IOService ioService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    @Before
    public void setUp() throws Exception {
        byte[] testCotent = "hello world!".getBytes();
        testFile = new VFSFile("root/test/", "testcase",new Date(), testCotent);
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void getContent(){
        assertEquals("hello world!", testFile.getContent());
    }

    @Test
    public void list(){
        testFile.list(true, true, ioService);
    }

    @Test
    public void getItem(){
        String[] cmd = {"sdf", "dsf"};
        VFSunit returnFile = testFile.getItem(cmd,ioService);
        assertEquals(testFile, returnFile);
        testFile.list(true, true, null);
        String path = testFile.getPath();
        assertEquals(path, "root/test/testcase");
        assertEquals(testFile.getName(), "testcase");
        VFSunit noName = new VFSFile("root/", null, new Date(1L), null);
        VFSunit noName1 = new VFSFile("root/", "1", new Date(1L), null);
        VFSunit noName2 = new VFSFile("root/", "1", new Date(1L), null);
        assertEquals(noName.getName(), ".NIL");
        assertEquals(noName.getDateCreated(), dateFormat.format(new Date(1L)));
        assertEquals(noName.toString(), "null - root/null - " + noName.getDateCreated());
        assertTrue(testFile.equals(testFile));
        assertFalse(testFile.equals(1));
        assertTrue(noName1.equals(noName2));
    }
}