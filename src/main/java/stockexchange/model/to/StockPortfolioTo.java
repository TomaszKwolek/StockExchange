package stockexchange.model.to;

public class StockPortfolioTo {

	private int id;
	private String companyCode;
	private Integer amount;
	private PlayerTo player;
	
	public StockPortfolioTo() {
		super();
	}
	
	public StockPortfolioTo(String companyCode, Integer amount, PlayerTo player) {
		super();
		this.companyCode = companyCode;
		this.amount = amount;
		this.player = player;
	}

	public StockPortfolioTo(int id, String companyCode, Integer amount, PlayerTo player) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.amount = amount;
		this.player = player;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public PlayerTo getPlayer() {
		return player;
	}

	public void setPlayer(PlayerTo player) {
		this.player = player;
	}


}