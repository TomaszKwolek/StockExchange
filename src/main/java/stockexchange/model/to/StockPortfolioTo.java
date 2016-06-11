package stockexchange.model.to;

public class StockPortfolioTo {

	private int id;
	private String companyCode;
	private int amount;
	
	public StockPortfolioTo() {
		super();
	}

	public StockPortfolioTo(int id, String companyCode, int volume) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.amount = volume;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	

}