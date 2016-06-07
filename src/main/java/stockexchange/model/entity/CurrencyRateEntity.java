package stockexchange.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the currency_rate database table.
 * 
 */
@Entity
@Table(name="currency_rate")
@NamedQuery(name="CurrencyRateEntity.findAll", query="SELECT c FROM CurrencyRateEntity c")
public class CurrencyRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="currency_pair_code")
	private String currencyPairCode;

	private BigDecimal rate;

	public CurrencyRateEntity() {
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