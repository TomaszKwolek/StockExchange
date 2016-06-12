package stockexchange.bank;

import java.math.BigDecimal;

public class CashBalance {
	
	private String currencyCode;
	private BigDecimal cashAmount;
	
	public CashBalance() {
		super();
	}

	public CashBalance(String currencyCode, BigDecimal cashAmount) {
		super();
		this.currencyCode = currencyCode;
		this.cashAmount = cashAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
		
}
