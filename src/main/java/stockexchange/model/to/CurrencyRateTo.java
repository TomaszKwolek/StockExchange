package stockexchange.model.to;

import java.math.BigDecimal;

public class CurrencyRateTo {

	private int id;
	private String currencyPairCode;
	private BigDecimal rate;

	public CurrencyRateTo() {
	}
	
	public CurrencyRateTo(int id, String currencyPairCode, BigDecimal rate) {
		super();
		this.id = id;
		this.currencyPairCode = currencyPairCode;
		this.rate = rate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrencyPairCode() {
		return this.currencyPairCode;
	}

	public void setCurrencyPairCode(String currencyPairCode) {
		this.currencyPairCode = currencyPairCode;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}