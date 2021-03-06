package hk.edu.polyu.comp3222.vfs.core.handler;

import hk.edu.polyu.comp3222.vfs.core.Util.IOService;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSDirectory;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSFile;
import hk.edu.polyu.comp3222.vfs.core.vfs.VFSunit;

import java.io.File;
import java.io.IOException;

/**
 * Created by Isaac on 2/17/17.
 */
public class ExportResponseHandler extends ResponseHandler{
    public VFSunit handlerResponse(String [] cmd, VFSDirectory Root, VFSDirectory CurrentDir, IOService ioService){
        ioService.printLine("This is the export handler");
        if(cmd.length != 2){
            ioService.printLine("export command expects at least/most one argument");
        }

        VFSunit tempFile = CurrentDir.getItem(cmd[1].split("/"), ioService);

        ioService.printLine(tempFile.getClass().toString());
        if(tempFile.getClass() == VFSDirectory.class){
            createDirectory((VFSDirectory) tempFile, ioService);
        }else if(tempFile.getClass() == VFSFile.class){
            createFile((VFSFile) tempFile, ioService);
        }

        return CurrentDir;
    }

    public void createFile(VFSFile outFile, IOService ioService){
        String tempPath = "host/" + outFile.getPath();
        File tempFile = new File(tempPath);
        try {
            tempFile.getParentFile().mkdirs();
            tempFile.createNewFile();
        }catch (IOException e){
            ioService.printLine("create file failed, action abort.");
        }
    }

    public void createDirectory(VFSDirectory outDir, IOService ioService){
        String tempPath = "host/" + outDir.getPath();
        File tempDir = new File(tempPath);
        if (!tempDir.exists()) {
            ioService.printLine("creating directory in host/: " + tempDir.getName());
            boolean result = false;

            try{
                tempDir.mkdirs();
                result = true;
            }
            catch(SecurityException se){
                //handle it
                ioService.printLine("create directory failed");
            }
            if(result) {
                ioService.printLine("DIR created");
            }
        }else{
            ioService.printLine("directory already exists. Operation abort");
        }
    }
}
