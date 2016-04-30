package it.Marco.Controller;

import java.util.Date;
import java.util.Timer;

public class FrontController {

	public void init() {
		
		long millis = 1000;  //minuti * secondi * millesimi
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new thread(), new Date(), millis);		
	}	
	public static void main (String Args[]){
		FrontController fc = new FrontController();
		fc.init();
	}
}
