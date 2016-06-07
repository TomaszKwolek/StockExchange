package stockexchange.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.datawriter.DataWriter;
import stockexchange.player.Player;


@Component
public class SimulationGameImpl implements SimulationGame{

//	@Autowired
//	private Player player;
	@Autowired
	private DataWriter dataWriter;
	
	@Override
	public void executeSimulation(){
		System.out.println("Dziala");
		dataWriter.saveAllIntoDB();
	}
	
}
