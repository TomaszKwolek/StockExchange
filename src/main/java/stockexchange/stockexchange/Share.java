package stockexchange.stockexchange;

import java.math.BigDecimal;

public class Share {

	private String companyCode;
	private BigDecimal price;
	
	public Share() {
		super();
	}

	public Share(String companyCode, BigDecimal price) {
		super();
		this.companyCode = companyCode;
		this.price = price;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
