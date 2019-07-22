package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import service.CommandService;

class ParkLotTest {
	CommandService cm = new CommandService();
	 private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    @Before
	    public void setUpStreams() {
	        System.setOut(new PrintStream(outContent));
	    }

	    @After
	    public void cleanUpStreams() {
	        System.setOut(null);
	    }

	
	void createLottest() {
        cm.createLot("6");
        assertEquals(6,cm.availableSlotList.size());
}
	
	
	    public void park() throws Exception {
	 
	       cm.park("KA-01-HH-9999", "White");
	       assertEquals("Sorry, parking lot is not created" ,outContent.toString());
		
		 cm.createLot("6"); 
		 cm.park("KA-01-HH-1234", "White");
		 cm.park("KA-01-HH-9999", "White"); 
		 assertEquals(4,cm.availableSlotList.size());
		 cm.park("KA-01-HH-1234", "White");
		 cm.park("KA-01-HH-9999", "White"); 
		 cm.park("KA-01-HH-1234", "White");
		 cm.park("KA-01-HH-9999", "White"); 
		 cm.park("KA-01-HH-1234", "White");
		 cm.park("KA-01-HH-9999", "White"); 
		
		 assertEquals("Sorry, parking lot is full" ,outContent.toString());
	    }
	  
	  


}
