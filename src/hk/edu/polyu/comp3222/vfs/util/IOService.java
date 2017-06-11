package hk.edu.polyu.comp3222.vfs.util;

/**
 * Created by lidawei on 03/04/2017.
 */
public interface IOService {
    /**
     * @return the input of user
     */
    String readLine();

    /**
     * @param str the information that will be shown to user
     */
    void printLine(String str);
}
