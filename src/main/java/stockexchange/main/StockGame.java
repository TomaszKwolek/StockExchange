package stockexchange.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stockexchange.simulation.SimulationGame;


public class StockGame {

	public static void main(String[] args) {
		
		ApplicationContext appContext = 
    		new ClassPathXmlApplicationContext("spring/context.xml");
		
	    SimulationGame simulationGame = (SimulationGame) appContext.getBean(SimulationGame.class);
	 
	    simulationGame.executeSimulation();
	    
	       
	}


}
