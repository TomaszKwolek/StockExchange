package stockexchange.model.to;

import java.math.BigDecimal;
import java.util.Date;


public class StockPriceTo {

	private int id;
	private String companyCode;
	private Date date;
	private BigDecimal price;
	
	public StockPriceTo() {
		super();
	}

	public StockPriceTo(int id, String companyCode, Date date, BigDecimal price) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.date = date;
		this.price = price;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}