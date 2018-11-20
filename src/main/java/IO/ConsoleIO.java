package IO;

import java.util.Scanner;

public class ConsoleIO implements IO{
    private Scanner scanner;
    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public String nextLine(){
        return scanner.nextLine();
    }
}
