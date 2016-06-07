package stockexchange.bank;

import java.math.BigDecimal;

public class PayementConfirmation {


	private String difitalSign;
	private String currencyCode;
	private BigDecimal amount;
	
	public PayementConfirmation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayementConfirmation(String difitalSign, String currencyCode, BigDecimal amount) {
		super();
		this.difitalSign = difitalSign;
		this.currencyCode = currencyCode;
		this.amount = amount;
	}

	public String getDifitalSign() {
		return difitalSign;
	}

	public void setDifitalSign(String difitalSign) {
		this.difitalSign = difitalSign;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
