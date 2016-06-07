package stockexchange.model.to;

public class PlayerTo {

	private int id;
	private String firstName;
	private String lastName;
	private String pesel;
	private CashPortfolioTo cashPortfolio;
	private StockPortfolioTo stockPortfolio;
	
	public PlayerTo() {
		super();
	}

	public PlayerTo(int id, String firstName, String lastName, String pesel, CashPortfolioTo cashPortfolio,
			StockPortfolioTo stockPortfolio) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
		this.cashPortfolio = cashPortfolio;
		this.stockPortfolio = stockPortfolio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public CashPortfolioTo getCashPortfolio() {
		return cashPortfolio;
	}

	public void setCashPortfolio(CashPortfolioTo cashPortfolio) {
		this.cashPortfolio = cashPortfolio;
	}

	public StockPortfolioTo getStockPortfolio() {
		return stockPortfolio;
	}

	public void setStockPortfolio(StockPortfolioTo stockPortfolio) {
		this.stockPortfolio = stockPortfolio;
	}

	

}