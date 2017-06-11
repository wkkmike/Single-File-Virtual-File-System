package hk.edu.polyu.comp3222.vfs.util;

/**
 * Created by lidawei on 05/04/2017.
 */
public enum CommandType{
    LIST("ls"),
    MOVE("mv"),
    QUIT("q"),
    RENAME("rn"),
    SEARCH("sr"),
    TOUCH("touch"),
    CD("cd"),
    MKDIR("mkdir"),
    HELP("help"),
    COPY("cp"),
    LINK("link"),
    IMPORT("import");


    private String command;

    private CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
