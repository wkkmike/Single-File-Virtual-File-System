package hk.edu.polyu.comp3222.vfs.core.Util;
import java.util.Scanner;
import java.io.PrintStream;
/**
 * Created by Isaac on 1/27/17.
 */
public class ConsoleIO implements IOService{

    private PrintStream output = new PrintStream(System.out);

    public void printInstructions(){
        output.println("This is the instruction!");
    }
    @Override
    public void printLine(String str) {
        output.println(str);
    }
    @Override
    public String readLine(String str){
        System.out.print(str);
        Scanner input = new Scanner(System.in);
        String nextLine = input.nextLine();
        return nextLine;
    }
    @Override
    public void printConsole(String str, Boolean inline) {
        if (inline) {
            System.out.printf("%s", str);
        }
    }

    public void printHelp(){
        System.out.println("This is the help");
    }

}
