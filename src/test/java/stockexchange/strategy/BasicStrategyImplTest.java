package stockexchange.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Offer;
import stockexchange.model.entity.StockPriceEntity;
import stockexchange.repository.StockExchangeRepository;
import stockexchange.stockexchange.Share;
import stockexchange.stockexchange.StockExchange;
import stockexchange.stockexchange.StockOfDay;
import stockexchange.strategy.Strategy;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "strategy-context.xml")
public class BasicStrategyImplTest {


	@Autowired
	BeanFactory beanFactory;
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnSixRecommendations() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = dateFormat.parse("20130102");
		CashBalance cashBalance = new CashBalance("PLN", new BigDecimal(10000.00));
		List<CashBalance> cashBalances = new ArrayList<>();
		cashBalances.add(cashBalance);
		//when
		List<Offer> recommendationsToBuy = beanFactory.getBean("basicStrategy", Strategy.class).prepareRecommendationsToBuy(cashBalances, date, 100);	
		// then
		assertEquals(recommendationsToBuy.size(), 6);
	}
	
	@Test
	@Sql(scripts = "import.sql")
	public void testShoulReturnThreeRecommendations() throws ParseException {
		// given
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = dateFormat.parse("20130102");
		CashBalance cashBalance = new CashBalance("PLN", new BigDecimal(10000.00));
		List<CashBalance> cashBalances = new ArrayList<>();
		cashBalances.add(cashBalance);
		//when
		List<Offer> recommendationsToBuy = beanFactory.getBean("basicStrategy", Strategy.class).prepareRecommendationsToBuy(cashBalances, date, 3);	
		// then
		assertEquals(recommendationsToBuy.size(), 3);
	}

	
}
