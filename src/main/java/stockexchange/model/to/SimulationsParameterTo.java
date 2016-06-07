package stockexchange.model.to;

import java.util.Date;


public class SimulationsParameterTo {

	private int id;
	private int currencyPriceFactor;
	private Date startDate;
	private int stockAvailibityFactor;
	private int stockPriceFactor;
	private Date stopDate;
	
	public SimulationsParameterTo() {
		super();
	}

	public SimulationsParameterTo(int id, int currencyPriceFactor, Date startDate, int stockAvailibityFactor,
			int stockPriceFactor, Date stopDate) {
		super();
		this.id = id;
		this.currencyPriceFactor = currencyPriceFactor;
		this.startDate = startDate;
		this.stockAvailibityFactor = stockAvailibityFactor;
		this.stockPriceFactor = stockPriceFactor;
		this.stopDate = stopDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrencyPriceFactor() {
		return currencyPriceFactor;
	}

	public void setCurrencyPriceFactor(int currencyPriceFactor) {
		this.currencyPriceFactor = currencyPriceFactor;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStockAvailibityFactor() {
		return stockAvailibityFactor;
	}

	public void setStockAvailibityFactor(int stockAvailibityFactor) {
		this.stockAvailibityFactor = stockAvailibityFactor;
	}

	public int getStockPriceFactor() {
		return stockPriceFactor;
	}

	public void setStockPriceFactor(int stockPriceFactor) {
		this.stockPriceFactor = stockPriceFactor;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	

}