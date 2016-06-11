package stockexchange.bank;

import java.math.BigDecimal;

public class ConfirmationFromBank {


	private String difitalSign;
	private String currencyCode;
	private BigDecimal amount;
	
	public ConfirmationFromBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfirmationFromBank(String difitalSign, String currencyCode, BigDecimal amount) {
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
