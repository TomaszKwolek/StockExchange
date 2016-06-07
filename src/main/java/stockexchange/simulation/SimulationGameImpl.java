package stockexchange.simulation;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import stockexchange.datawriter.DataWriter;



@Component
public class SimulationGameImpl implements SimulationGame{

//	@Autowired
//	private Player player;
	@Autowired
	private DataWriter dataWriter;
	
	private static final Logger log4j = LogManager.getLogger(SimulationGameImpl.class.getName());
	
	@Override
	public void executeSimulation(){
		try {
			dataWriter.saveAllIntoDB();
			log4j.log(Level.forName("NOTICE", 150), "Input data loaded successful!");
		} catch (IOException e) {
			log4j.log(Level.forName("NOTICE", 150), "IOException: Input data cannot be loaded!");
		}
	}
	
	
}
