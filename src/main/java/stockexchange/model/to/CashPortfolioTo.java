package stockexchange.model.to;

import java.math.BigDecimal;

import stockexchange.model.entity.PlayerEntity;



public class CashPortfolioTo {

	private int id;
	private BigDecimal amount;
	private String currencyCode;
	private PlayerTo player;

	public CashPortfolioTo() {
	}

	public CashPortfolioTo(int id, BigDecimal amount, String currencyCode) {
		super();
		this.id = id;
		this.amount = amount;
		this.currencyCode = currencyCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public PlayerTo getPlayer() {
		return player;
	}

	public void setPlayer(PlayerTo player) {
		this.player = player;
	}
	

}