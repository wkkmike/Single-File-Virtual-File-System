package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

import java.util.ArrayList;

import static hk.edu.polyu.comp3222.vfs.util.CommandType.*;

/**
 * Created by lidawei on 05/04/2017.
 */
public class CommandReciever {
    public CommandReciever() {
    }

    public void execute(String command, ArrayList<String> cmd, IOService output, VirtualDisk disk) {
        switch (command) {
            case "cp":
                CopyCmd copy = new CopyCmd();
                copy.command(cmd, output, disk);
                break;
            case "cd":
                NavigationCmd cd = new NavigationCmd();
                cd.command(cmd, output, disk);
                break;
            case "ls":
                ListCmd ls = new ListCmd();
                ls.command(cmd, output, disk);
                break;
            case "mkdir":
                MkdirCmd mk = new MkdirCmd();
                mk.command(cmd, output, disk);
                break;
            case "mv":
                MoveCmd mv = new MoveCmd();
                mv.command(cmd, output, disk);
                break;
            case "q":
                QuitCmd q = new QuitCmd();
                q.command(cmd, output, disk);
                break;
            case "rename":
                RenameCmd r = new RenameCmd();
                r.command(cmd, output, disk);
                break;
            case "search":
                SearchCmd s = new SearchCmd();
                s.command(cmd, output, disk);
                break;
            case "help":
                HelpCmd h = new HelpCmd();
                h.command(cmd, output, disk);
                break;
            case "touch":
                TouchCmd t = new TouchCmd();
                t.command(cmd, output, disk);
                break;
            case "import":
                ImportCmd i = new ImportCmd();
                i.command(cmd, output, disk);
                break;
            case "store":
                StoreCmd store = new StoreCmd();
                store.command(cmd,output,disk);
                break;
            case "remain":
                RemainSizeCmd remain = new RemainSizeCmd();
                remain.command(cmd,output,disk);
                break;
            case "occupy":
                OccupiedSizeCmd occupy = new OccupiedSizeCmd();
                occupy.command(cmd,output,disk);
                break;
            case "delete":
                DeleteCmd del = new DeleteCmd();
                del.command(cmd,output,disk);
                break;
            case "export":
                ExportCmd ex = new ExportCmd();
                ex.command(cmd,output,disk);
                break;
            case "connect":
                ConnectCmd con = new ConnectCmd();
                con.command(output, disk);
                break;
        }

    }
}
