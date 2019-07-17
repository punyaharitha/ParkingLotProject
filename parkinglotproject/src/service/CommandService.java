package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import testing.CarDetails;

import testing.CarDetails.Colour;

public class CommandService {
    
    private static int parking_slot_size=0;
    private static ArrayList<Integer> availableSlotList;
    private static Map<String, CarDetails> allocateSlot;
    private static Map<String, String> reg2slotMap;
    private static Map<Colour, ArrayList<String>> colour2RegListMap;
    public CommandService() {
        super();
    }
    
    public void createLot(String size) {
            try {
                parking_slot_size = Integer.parseInt(size);
            
            availableSlotList = new ArrayList<Integer>() {};
            for (int i=1; i<= parking_slot_size; i++) {
                availableSlotList.add(i);
            }
            allocateSlot = new HashMap<String, CarDetails>();
            reg2slotMap = new HashMap<String, String>();
            colour2RegListMap = new HashMap<Colour, ArrayList<String>>();
            System.out.println("Created parking lot with " + size + " slots\n");
            } catch (Exception e) {
                System.out.println("Invalid lot count\n");
            }
        }
    public void park(String regNo, String color) {
            if (parking_slot_size == 0) {
                System.out.println("Sorry, parking lot is not created\n");
            } else if (allocateSlot.size() == parking_slot_size) {
                System.out.println("Sorry, parking lot is full\n");
            } else {
                Collections.sort(availableSlotList);
                String slot = availableSlotList.get(0).toString();
                CarDetails car = new CarDetails(regNo, color,slot);
                allocateSlot.put(slot, car);
                reg2slotMap.put(regNo, slot);
                if (colour2RegListMap.containsKey(car.getColour())) {
                    ArrayList<String> regNoList = colour2RegListMap.get(car.getColour());
                    colour2RegListMap.remove(car.getColour());
                    regNoList.add(regNo);
                    colour2RegListMap.put(car.getColour(), regNoList);
                } else {
                    ArrayList<String> regNoList = new ArrayList<String>();
                    regNoList.add(regNo);
                    colour2RegListMap.put(car.getColour(), regNoList);
                }
                System.out.println("Allocated slot number: " + slot+"\n");
                availableSlotList.remove(0);
            }
        }
    public void leave(String slotNo) {
           if (parking_slot_size == 0) {
               System.out.println("Sorry, parking lot is not created");
               System.out.println();
           } else if (allocateSlot.size() > 0) {
               CarDetails CarDetailsToLeave = allocateSlot.get(slotNo);
               if (CarDetailsToLeave != null) {
                   allocateSlot.remove(slotNo);
                   reg2slotMap.remove(CarDetailsToLeave.getRegNo());
                   ArrayList<String> regNoList = colour2RegListMap.get(CarDetailsToLeave.getColour());
                   if (regNoList.contains(CarDetailsToLeave.getRegNo())) {
                       regNoList.remove(CarDetailsToLeave.getRegNo());
                   }
                   // Add the Lot No. back to available slot list.
                   availableSlotList.add(Integer.parseInt(slotNo));
                   System.out.println("Slot number " + slotNo + " is free\n");
               } else {
                   System.out.println("Slot number " + slotNo + " is already empty\n");
               }
           } else {
               System.out.println("Parking lot is empty\n");
           }
       }
    public void status() {
            if (parking_slot_size == 0) {
                System.out.println("Sorry, parking lot is not created\n");
            } else if (allocateSlot.size() > 0) {
                System.out.println("================================================================"); 
                System.out.println("| S.No\t|\tRegistration No.\t|\tColor\t\t|"); 
                System.out.println("================================================================"); 
                CarDetails CarDetails;
                for (int i = 1; i <= parking_slot_size; i++) {
                    String key = Integer.toString(i);
                    if (allocateSlot.containsKey(key)) {
                        CarDetails = allocateSlot.get(key);
                        System.out.println("| "+i + "\t|\t" + CarDetails.getRegNo() + "\t|\t\t" + CarDetails.getColour()+"\t\t|");
                    }
                }
                System.out.println("================================================================\n"); 
            } else {
                System.out.println("Parking lot is empty\n");
            }
        }
        public void getRegistrationNumbersFromColor(String color) {
            if (parking_slot_size == 0) {
                System.out.println("Sorry, parking lot is not created");
                System.out.println();
            } else if (colour2RegListMap.containsKey(Colour.valueOf(color.toUpperCase()))) {
                ArrayList<String> regNoList = colour2RegListMap.get(Colour.valueOf(color.toUpperCase()));
                System.out.println();
                for (int i=0; i < regNoList.size(); i++) {
                    if (!(i==regNoList.size() - 1)){
                        System.out.print(regNoList.get(i) + ",");
                    } else {
                        System.out.print(regNoList.get(i));
                        System.out.print("");
                    }
                }
            } else {
                System.out.println("Not found\n");
            }
        }
        public void getSlotNumbersFromColor(String color) {
            if (parking_slot_size == 0) {
                System.out.println("Sorry, parking lot is not created");
                System.out.println();
            } else if (colour2RegListMap.containsKey(Colour.valueOf(color.toUpperCase()))) {
                ArrayList<String> regNoList = colour2RegListMap.get(Colour.valueOf(color.toUpperCase()));
                ArrayList<Integer> slotList = new ArrayList<Integer>();
                System.out.println();
                for (int i=0; i < regNoList.size(); i++) {
                    slotList.add(Integer.valueOf(reg2slotMap.get(regNoList.get(i))));
                }
                Collections.sort(slotList);
                for (int j=0; j < slotList.size(); j++) {
                    if (!(j == slotList.size() - 1)) {
                        System.out.print(slotList.get(j) + ",");
                    } else {
                        System.out.print(slotList.get(j));
                        System.out.print("");

                    }
                }
                System.out.println();
            } else {
                System.out.println("Not found");
                System.out.println();
            }
        }
        public void getSlotNumberFromRegNo(String regNo) {
            if (parking_slot_size == 0) {
                System.out.println("Sorry, parking lot is not created");
                System.out.println();
            } else if (reg2slotMap.containsKey(regNo)) {
                System.out.println(reg2slotMap.get(regNo));
            } else {
                System.out.println("Not found");
                System.out.println();
            }
        }
}
