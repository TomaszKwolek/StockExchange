package stockexchange.simulation;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import stockexchange.calendar.StockExchangeCalendar;
import stockexchange.datawriter.DataWriter;
import stockexchange.mapper.DateParser;
import stockexchange.player.Player;



@Component
public class SimulationGameImpl implements SimulationGame{

	@Autowired
	private Player player;
	@Autowired
	private DataWriter dataWriter;
	@Autowired
	private SimulationParameters simParam;
	@Autowired
	private DateParser dateParser;
	@Autowired
	private StockExchangeCalendar calendar;
	
	private static final Logger log4j = LogManager.getLogger(SimulationGameImpl.class.getName());
	
	@Override
	public void executeSimulation(){
		try {
			dataWriter.saveAllIntoDB();
			updateStockExchangeCalendar();
			player.executeBuyStrategy(new Date());
			
		} catch (IOException e) {
			log4j.log(Level.forName("NOTICE", 150), "IOException: Input data cannot be loaded!");
		//} catch (ParseException e) {
		//	log4j.log(Level.forName("NOTICE", 150), "Date parameter not correct!");
		}

	}
	
	private void updateStockExchangeCalendar(){
		calendar.updateFirstDateOnSE();
		calendar.udpateLastDateOnSE();
		calendar.setCurrentDate(calendar.getFirstDateOnSE());
	}
	
}
