package model;

import java.util.Arrays;
import java.util.Scanner;

import service.Processor;

import utility.Constants;

public class Main {

    public static void main(String[] args) {
        Processor processor=new Processor();
        switch (args.length) {
        case 0:
        Scanner in = new Scanner(System.in); 
        while(true){
            System.out.println("Enter your query..");
            String command =in.nextLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Exiting please wait..");
                break;
            }
            
            System.out.println("You have entered : "+command );
            processor.getCommandMethod(command,in);    
           
        }
        break;
        case 1:
            // File input/output
            processor.getCommandFileInput(args[0]);
            break;
        default:
            System.out.println("Invalid input. Usage: java -jar <jar_file_path> <input_file_path>");

    }
    }
}
