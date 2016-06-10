package stockexchange.stockexchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockOfDay {

	private Date date;
	private List<Share> stock = new ArrayList<Share>();
	
	public StockOfDay() {
		super();
	}

	public StockOfDay(Date date) {
		super();
		this.date = date;
	}
	
	public StockOfDay(Date date, List stock) {
		super();
		this.date = date;
		this.stock = stock;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List getStock() {
		return stock;
	}

	public void setStock(List stock) {
		this.stock = stock;
	}
	
	public void addShareToStock(Share share){
		this.stock.add(share);
	}
		
}
