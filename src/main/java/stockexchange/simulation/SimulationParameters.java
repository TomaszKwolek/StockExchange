package stockexchange.simulation;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import stockexchange.mapper.DateParser;

@Component
public class SimulationParameters {

	
	@Value(value = "#{simulationProperties['startDate']}")
	private String startDate;
	@Value(value = "#{simulationProperties['stopDate']}")
	private String stopDate;
	@Value(value = "#{simulationProperties['stockPriceFactor']}")
	private int stockPriceFactor;
	@Value(value = "#{simulationProperties['currencyPriceFactor']}")
	private int currencyPriceFactor;
	@Value(value = "#{simulationProperties['stockAvailibityFactor']}")
	private int stockAvailibityFactor;
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getStopDate() {
		return stopDate;
	}
	
	public int getStockPriceFactor() {
		return stockPriceFactor;
	}
	
	public int getCurrencyPriceFactor() {
		return currencyPriceFactor;
	}
	
	public int getStockAvailibityFactor() {
		return stockAvailibityFactor;
	}
	
}
