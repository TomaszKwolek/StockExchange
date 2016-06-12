package stockexchange.brokerage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.bank.ConfirmationFromBank;
import stockexchange.player.BrokerageAuthentication;
import stockexchange.stockexchange.StockOfDay;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "brokerage-context.xml")
public class BrokerageTest {


	@Autowired
	private Brokerage brokerage;
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnOfferList() throws ParseException {
		// given
		BigDecimal priceZero = new BigDecimal(0);
		List<Offer> offersInquiry = new ArrayList();
		offersInquiry.add(new Offer("PZU", 100, new BigDecimal(0)));
		offersInquiry.add(new Offer("PKO", 100, new BigDecimal(0)));
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = dateFormat.parse("20130102"); 
		//when
		List<Offer> result = brokerage.prepareListOfOffersToBuy(offersInquiry, date);
		// then
		assertEquals(result.size(), 2);
		assertEquals(result.get(0).getCompanyCode(), "PZU");
		assertEquals(result.get(1).getCompanyCode(), "PKO");
		if(result.get(0).getPrice().compareTo(priceZero) != 1){
			fail();
		}
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnSharesBalance(){
		// given
		BrokerageAuthentication authentication = new BrokerageAuthentication();
		authentication.setPesel("86031701659");
		//when
		List<ShareBalance> result = brokerage.getSharesBalances(authentication);
		// then
		assertEquals(result.size(), 1);
		assertEquals( 45, (int)result.get(0).getAmount());
	}
	
	@Test
	@Sql(scripts = "import2.sql")
	public void testShoulReturnUpdatedStockPortfolio() {
		// given
		BrokerageAuthentication authentication = new BrokerageAuthentication();
		authentication.setPesel("86031701659");
		List<Offer> sharesToBuy = new ArrayList();
		sharesToBuy.add(new Offer("PZU", 100, new BigDecimal(5)));
		sharesToBuy.add(new Offer("PKO", 100, new BigDecimal(5)));
		//when
		brokerage.confirmBuy(authentication, sharesToBuy, new ConfirmationFromBank());
		List<ShareBalance> result = brokerage.getSharesBalances(authentication);
		// then
		assertEquals(result.size(), 2);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFiveStockOfDays() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateFrom = dateFormat.parse("20130102"); 
		Date dateTo = dateFormat.parse("20131102"); 
		//when
		List<StockOfDay> result = brokerage.getStockOfDays(dateFrom, dateTo);
		// then
		assertEquals(result.size(), 5);
		assertEquals(result.get(0).getStock().size(), 3);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnFiveStockOfDaysForCompany() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateFrom = dateFormat.parse("20130102"); 
		Date dateTo = dateFormat.parse("20131102"); 
		//when
		List<StockOfDay> result = brokerage.getStockOfDaysForCompany("PZU", dateFrom, dateTo);
		// then
		assertEquals(result.size(), 5);
		assertEquals(result.get(0).getStock().size(), 1);
	}
	
}
