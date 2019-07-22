package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.CarDetails;
import utility.Constants;

public class Processor {
    private static final Map<Integer,String> commandMap=new HashMap<Integer,String>();
    private static CommandService service;
    public Processor(){
        super();
        service=new CommandService();
        commandMap.put(0, "Error");
        commandMap.put(1, Constants.create_parking_lot+" a1");
        commandMap.put(2, Constants.park+" a1 a2");
        commandMap.put(3, Constants.leave+" a1");
        commandMap.put(4, Constants.status);
        commandMap.put(5, Constants.registration_numbers_for_cars_with_colour+" a1");
        commandMap.put(6, Constants.slot_numbers_for_cars_with_colour+" a1");
        commandMap.put(7, Constants.slot_number_for_registration_number+" a1");
    }
    
//    public int getNearestSlot(){
//        return 0;
//    }
//    public CarDetails[] createParkingSlot(int size){
//        return new CarDetails[size];
//    }
    public void getCommandMethod(String command,Scanner in){
//        System.out.println(command); 
        String commandValues[]=command.split(" ");

//        String commandType=(command).substring(0, (command+" ").indexOf(" "));
        String commandType=commandValues[0];
        System.out.println("======command========="+commandValues[0]);
        switch(commandType) 
                { 
                    case Constants.create_parking_lot: 
//                        System.out.println("create_parking_lot"); 
                        service.createLot(commandValues[1]);
                        break; 
                    case Constants.park: 
//                        System.out.println("park"); 
                        service.park(commandValues[1], commandValues[2]);
                        break; 
                    case Constants.leave: 
//                        System.out.println("leave"); 
                       service.leave(commandValues[1]);
                        break;
                    case Constants.status: 
//                        System.out.println("status"); 
                       service.status();
                        break;
                    case Constants.registration_numbers_for_cars_with_colour: 
//                       System.out.println("registration_numbers_for_cars_with_colour"); 
                       service.getRegistrationNumbersFromColor(commandValues[1]);
                       break;
                   case Constants.slot_numbers_for_cars_with_colour: 
//                       System.out.println("slot_numbers_for_cars_with_colour"); 
                       service.getSlotNumbersFromColor(commandValues[1]);
                       break;
                    case Constants.slot_number_for_registration_number: 
//                       System.out.println("slot_number_for_registration_number"); 
                       service.getSlotNumberFromRegNo(commandValues[1]);
                       break;                       
                    default: 
                    System.out.println("\n\nYou have entered an invalid command. Please Try again with any of below commands.\n"); 
                    printCommandsTable();
                    System.out.println("\nPlease enter your choice.."); 
                    String input =in.nextLine();
                       System.out.println(input); 

            if(input.equalsIgnoreCase("exit")){
                break;
            }else if(!input.isEmpty()){
                try{
                    int choice =Integer.parseInt(input);
                
                    executeChoice(choice, in);
                }catch(NumberFormatException ex){
                    System.out.println("Please enter number between 1~7.."); 

                }
            }else{
                executeChoice(0, in);

            }

                   } 
        
    }
    public void executeChoice(int choice,Scanner in){
//        in.close();
//        in=new Scanner(System.in);
       String choiceCommand;
           choiceCommand=commandMap.get(choice);
       if(choiceCommand.contains("a1")){
           System.out.println("Please enter the first argument."); 
           String a1 =in.nextLine();
           while(a1.isEmpty()){
               System.out.println("Please enter the first argument."); 
               a1 =in.nextLine();
           }
           choiceCommand=choiceCommand.replaceAll("a1", a1);
       }else if(choiceCommand.contains("a2")){
           System.out.println("Please enter the second argument."); 
           String a2 =in.nextLine();
           while(a2.isEmpty()){
               System.out.println("Please enter the first argument."); 
               a2 =in.nextLine();
           }
           choiceCommand=choiceCommand.replaceAll("a2", a2);
       }
        getCommandMethod(choiceCommand,in);
    }
    public void printCommandsTable(){
        System.out.println("===================================================================="); 
        System.out.println("|S.No  |               Command                                      |"); 
        System.out.println("===================================================================="); 
        System.out.println("| 1.   |   "+Constants.create_parking_lot+" <Parking Lot Size>                    |"); 
        System.out.println("| 2.   |   "+Constants.park+" <Car Reg Number> <Colour Of Car>                    |"); 
        System.out.println("| 3.   |   "+Constants.leave+" <Slot Number>                                      |"); 
        System.out.println("| 4.   |   "+Constants.status+"                                                   |"); 
        System.out.println("| 5.   |   "+Constants.registration_numbers_for_cars_with_colour+" <Colour of Car>|"); 
        System.out.println("| 6.   |   "+Constants.slot_numbers_for_cars_with_colour+" <Colour of Car>        |"); 
        System.out.println("| 7.   |   "+Constants.slot_number_for_registration_number+" <Car Reg Number      |"); 
        System.out.println("===================================================================="); 
        
    }
    
    public void getCommandFileInput(String filePath) {
        // Assuming input to be a valid file path.
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                  
                   getCommandMethod(line.trim(), null);
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }
    
	/*
	 * public static void main(String[] args) { Processor processor=new Processor();
	 * 
	 * Scanner in = new Scanner(System.in); while(true){
	 * System.out.println("Enter your query.."); String command =in.nextLine();
	 * if(command.equalsIgnoreCase("exit")){
	 * System.out.println("Exiting please wait.."); break; } else
	 * if(command.equalsIgnoreCase("exit")) {
	 * 
	 * 
	 * } System.out.println("You have entered : "+command );
	 * 
	 * processor.getCommandMethod(command,in);
	 * 
	 * } }
	 */}
