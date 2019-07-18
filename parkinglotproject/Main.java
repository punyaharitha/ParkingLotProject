package model;

import java.util.Arrays;
import java.util.Scanner;

import service.Processor;

import utility.Constants;

public class Main {

    public static void main(String[] args) {
        Processor processor=new Processor();
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
    }
    }

