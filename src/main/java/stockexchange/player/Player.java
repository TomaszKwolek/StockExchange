package stockexchange.player;

import stockexchange.strategy.BasicStrategyImpl;
import stockexchange.strategy.Strategy;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import stockexchange.bank.Bank;
import stockexchange.bank.BankImpl;
import stockexchange.bank.CashBalance;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;
import stockexchange.datawriter.DataWriterImpl;
import stockexchange.model.to.StockPriceTo;

@Service
public class Player {

	@Autowired
	BeanFactory beanFactory;
	//@Autowired
	//private BrokerageImpl brokerageimpl;
	@Autowired
	private Bank bank;
	
	@Value(value = "#{simulationProperties['playerPesel']}")
	private String playerPesel;
	@Value(value = "#{simulationProperties['strategy']}")
	private String strategy;
	
	private static final Logger log4j = LogManager.getLogger(Player.class.getName());

	public void executeBuyStrategy(Date date) { 
		List<CashBalance>  cashBalances = bank.getCashBalance(playerPesel, new BankAuthentication());
		printCashBalance(cashBalances);
		beanFactory.getBean(strategy, Strategy.class).prepareRecommendationsToBuy(cashBalances, date);

		
		
	 }
	
	public void executeSellStartegy(Date date) { 
		// TODO Auto-generated method
	 } 

	private List<Offer> makeDecisonToBuy(List<Offer> listFromStrategy, List<Offer> offerFromBrokerage) {
		return offerFromBrokerage; 
		// TODO Auto-generated method
	 }
	
	private List<Offer> makeDecisionToSell(List<Offer> listFromStrategy, List<Offer> offerFromBrokerage) { 
		// TODO Auto-generated method
		return null;
	 }
	
	private void printCashBalance(List<CashBalance> cashBalances){
		String cashBalanceToPrint = "";
		for (CashBalance cashBalance : cashBalances) {
			cashBalanceToPrint+= cashBalance.getCurrencyCode() + ": "+ cashBalance.getCashAmount()+" | ";
		}
		log4j.log(Level.forName("NOTICE", 150), "Cash Balance at the beginning of the simulation: "+cashBalanceToPrint);
	}

}
