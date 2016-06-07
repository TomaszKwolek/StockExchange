package stockexchange.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the cash_portfolio database table.
 * 
 */
@Entity
@Table(name="cash_portfolio")
@NamedQuery(name="CashPortfolioEntity.findAll", query="SELECT c FROM CashPortfolioEntity c")
public class CashPortfolioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal amount;

	@Column(name="currency_code")
	private String currencyCode;

	//bi-directional many-to-one association to PlayerEntity
	@ManyToOne
	private PlayerEntity player;

	public CashPortfolioEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public PlayerEntity getPlayer() {
		return this.player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

}