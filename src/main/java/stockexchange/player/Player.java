package stockexchange.player;

import stockexchange.strategy.Strategy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockexchange.bank.BankImpl;
import stockexchange.brokerage.BrokerageImpl;
import stockexchange.brokerage.Offer;

//@Service
public class Player {

	//@Autowired
	//public Strategy startegy;
	//@Autowired
	//public BrokerageImpl brokerageimpl;
	//@Autowired
	//public BankImpl bank;

	public void executeBuyStrategy(Date date) { 
		// TODO Auto-generated method
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
