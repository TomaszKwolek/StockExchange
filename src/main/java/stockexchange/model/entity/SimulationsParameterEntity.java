package stockexchange.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the simulations_parameters database table.
 * 
 */
@Entity
@Table(name="simulations_parameters")
@NamedQuery(name="SimulationsParameterEntity.findAll", query="SELECT s FROM SimulationsParameterEntity s")
public class SimulationsParameterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="currency_price_factor")
	private int currencyPriceFactor;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="stock_availibity_factor")
	private int stockAvailibityFactor;

	@Column(name="stock_price_factor")
	private int stockPriceFactor;

	@Temporal(TemporalType.DATE)
	@Column(name="stop_date")
	private Date stopDate;

	public SimulationsParameterEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrencyPriceFactor() {
		return this.currencyPriceFactor;
	}

	public void setCurrencyPriceFactor(int currencyPriceFactor) {
		this.currencyPriceFactor = currencyPriceFactor;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStockAvailibityFactor() {
		return this.stockAvailibityFactor;
	}

	public void setStockAvailibityFactor(int stockAvailibityFactor) {
		this.stockAvailibityFactor = stockAvailibityFactor;
	}

	public int getStockPriceFactor() {
		return this.stockPriceFactor;
	}

	public void setStockPriceFactor(int stockPriceFactor) {
		this.stockPriceFactor = stockPriceFactor;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

}