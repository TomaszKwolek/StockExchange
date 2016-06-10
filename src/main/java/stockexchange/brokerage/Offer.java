package stockexchange.brokerage;

import java.math.BigDecimal;

public class Offer {

	private String companyCode;
	private Integer amount;
	private BigDecimal price;
	

	public Offer(String companyCode) {
		super();
		this.companyCode = companyCode;
	}
	

	public Offer(String companyCode, Integer amount, BigDecimal price) {
		super();
		this.companyCode = companyCode;
		this.amount = amount;
		this.price = price;
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
	public BigDecimal getPrice() {
		return price;
	
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
