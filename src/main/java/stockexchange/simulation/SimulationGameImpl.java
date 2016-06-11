package stockexchange.simulation;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import stockexchange.calendar.StockExchangeCalendar;
import stockexchange.datawriter.DataWriter;
import stockexchange.exceptions.NoStocksDataForDayException;
import stockexchange.exceptions.WrongDateParameterException;
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
			Date startDate = dateParser.stringToDate(simParam.getStartDate());
			Date stopDate =  dateParser.stringToDate(simParam.getStopDate());
			Date currentDate = startDate;
			int stopDateCompareResult = -1;
			
			dataWriter.saveAllIntoDB();
			updateStockExchangeCalendar();
			validateDateParameters();
			while(stopDateCompareResult < 0){
				executeSimulationForSingleDay(currentDate);
				stopDateCompareResult = currentDate.compareTo(stopDate);
				currentDate = calendar.getNextWorkingDay(currentDate);
			}	
		} catch (IOException e) {
			log4j.log(Level.forName("NOTICE", 150), "IOException: Input data cannot be loaded!");
		} catch (ParseException e) {
			log4j.log(Level.forName("NOTICE", 150), "Date parameter format is not correct!");
		} catch (WrongDateParameterException e) {
			log4j.log(Level.forName("NOTICE", 150), "Date parameter is not correct!");
		}

	}

	private void executeSimulationForSingleDay(Date currentDate) {
		try{
		player.executeBuyStrategy(currentDate);
		//player.executeSellStartegy(currentDate);
		log4j.log(Level.forName("NOTICE", 150), "------------------------------------------------------");
		} catch (NoStocksDataForDayException e){
			log4j.log(Level.forName("NOTICE", 150), "No stock for date: "+currentDate+" !");
		}
	}

	private void validateDateParameters() throws ParseException, WrongDateParameterException {
		Date startDate = dateParser.stringToDate(simParam.getStartDate());
		Date stopDate =  dateParser.stringToDate(simParam.getStopDate());
		if(!areDateParametersCorrect(startDate, stopDate)){
			 throw new WrongDateParameterException("Date parameter are not correct!");
		}
	}
	
	private void updateStockExchangeCalendar(){
		calendar.updateFirstDateOnSE();
		calendar.udpateLastDateOnSE();
	}
	
	private boolean areDateParametersCorrect(Date startDate, Date stopDate){
		Date firstDateOnSe = calendar.getFirstDateOnSE();
		Date lastDateOnSe = calendar.getLastDateOnSE();
		int compResultStartDate = startDate.compareTo(firstDateOnSe);
		int compResultLastDate = stopDate.compareTo(lastDateOnSe); 
		return compResultStartDate >= 0 && compResultLastDate<= 0;
	}
	

}
