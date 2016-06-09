package stockexchange.player;

import stockexchange.strategy.Strategy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import stockexchange.bank.Bank;
import stockexchange.bank.BankImpl;
import stockexchange.bank.CashBalance;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;

@Service
public class Player {

	//@Autowired
	//private Strategy startegy;
	//@Autowired
	//private BrokerageImpl brokerageimpl;
	@Autowired
	private Bank bank;
	
	@Value(value = "#{simulationProperties['playerPesel']}")
	private String playerPesel;

	public void executeBuyStrategy(Date date) { 
		List<CashBalance>  cashBalance = bank.getCashBalance(playerPesel, new BankAuthentication());
		
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

}
