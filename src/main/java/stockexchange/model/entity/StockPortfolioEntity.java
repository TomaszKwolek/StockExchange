package stockexchange.model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stock_portfolio database table.
 * 
 */
@Entity
@Table(name="stock_portfolio")
@NamedQuery(name="StockPortfolioEntity.findAll", query="SELECT s FROM StockPortfolioEntity s")
public class StockPortfolioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="company_code")
	private String companyCode;

	private Integer amount;

	//bi-directional many-to-one association to PlayerEntity
	@ManyToOne
	private PlayerEntity player;

	public StockPortfolioEntity() {
	}
	
	public StockPortfolioEntity(String companyCode, Integer amount, PlayerEntity player) {
		super();
		this.companyCode = companyCode;
		this.amount = amount;
		this.player = player;
	}

	public StockPortfolioEntity(int id, String companyCode, Integer amount, PlayerEntity player) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.amount = amount;
		this.player = player;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public PlayerEntity getPlayer() {
		return this.player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

}