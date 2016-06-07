package stockexchange.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the player database table.
 * 
 */
@Entity
@Table(name="player")
@NamedQuery(name="PlayerEntity.findAll", query="SELECT p FROM PlayerEntity p")
public class PlayerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String pesel;


	@OneToMany(mappedBy="player", cascade={CascadeType.ALL})
	private List<CashPortfolioEntity> cashPortfolios;


	@OneToMany(mappedBy="player")
	private List<StockPortfolioEntity> stockPortfolios;

	public PlayerEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public List<CashPortfolioEntity> getCashPortfolios() {
		return this.cashPortfolios;
	}

	public void setCashPortfolios(List<CashPortfolioEntity> cashPortfolios) {
		this.cashPortfolios = cashPortfolios;
	}

	public CashPortfolioEntity addCashPortfolio(CashPortfolioEntity cashPortfolio) {
		getCashPortfolios().add(cashPortfolio);
		cashPortfolio.setPlayer(this);

		return cashPortfolio;
	}

	public CashPortfolioEntity removeCashPortfolio(CashPortfolioEntity cashPortfolio) {
		getCashPortfolios().remove(cashPortfolio);
		cashPortfolio.setPlayer(null);

		return cashPortfolio;
	}

	public List<StockPortfolioEntity> getStockPortfolios() {
		return this.stockPortfolios;
	}

	public void setStockPortfolios(List<StockPortfolioEntity> stockPortfolios) {
		this.stockPortfolios = stockPortfolios;
	}

	public StockPortfolioEntity addStockPortfolio(StockPortfolioEntity stockPortfolio) {
		getStockPortfolios().add(stockPortfolio);
		stockPortfolio.setPlayer(this);

		return stockPortfolio;
	}

	public StockPortfolioEntity removeStockPortfolio(StockPortfolioEntity stockPortfolio) {
		getStockPortfolios().remove(stockPortfolio);
		stockPortfolio.setPlayer(null);

		return stockPortfolio;
	}

}