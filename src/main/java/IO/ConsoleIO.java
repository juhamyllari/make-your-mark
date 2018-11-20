package IO;

import java.util.Scanner;

public class ConsoleIO implements IO{
    private Scanner scanner;
    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }
    
    @Override
    public String nextLine(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public void print(String print) {
        System.out.println(print);
    }
}
