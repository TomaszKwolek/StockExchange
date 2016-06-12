package stockexchange.parameters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parameters {

	
	@Value(value = "#{simulationProperties['startDate']}")
	private String startDate;
	@Value(value = "#{simulationProperties['stopDate']}")
	private String stopDate;
	@Value(value = "#{simulationProperties['playerPesel']}")
	private String playerPesel;
	@Value(value = "#{simulationProperties['playerId']}")
	private String playerId;
	@Value(value = "#{simulationProperties['strategy']}")
	private String strategy;
	@Value(value = "#{simulationProperties['currencyCode']}")
	private String currencyCode;
	@Value(value = "#{simulationProperties['commissionFactor']}")
	private String commissionFactor;
	@Value(value = "#{simulationProperties['minCommission']}")
	private String minCommission;
	@Value(value = "#{simulationProperties['stockPriceFactor']}")
	private String stockPriceFactor;
	@Value(value = "#{simulationProperties['stockAvailibityFactor']}")
	private String stockAvailibityFactor;
	
	
	public String getStartDate() {
		return startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public String getPlayerPesel() {
		return playerPesel;
	}
	public String getPlayerId() {
		return playerId;
	}
	public String getStrategy() {
		return strategy;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public String getCommissionFactor() {
		return commissionFactor;
	}
	public String getMinCommission() {
		return minCommission;
	}
	public String getStockPriceFactor() {
		return stockPriceFactor;
	}
	public String getStockAvailibityFactor() {
		return stockAvailibityFactor;
	}
	

	
}
