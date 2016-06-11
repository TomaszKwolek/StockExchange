package stockexchange.player;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import stockexchange.bank.CashBalance;
import stockexchange.brokerage.Offer;
import stockexchange.brokerage.ShareBalance;

public class PlayerLogPrinter {
	
	private static final Logger log4j = LogManager.getLogger(PlayerLogPrinter.class.getName());

	public static void printCashBalance(List<CashBalance> cashBalances, Date date){
		String cashBalanceToPrint = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (CashBalance cashBalance : cashBalances) {
			cashBalanceToPrint+= cashBalance.getCurrencyCode() + ": "+ cashBalance.getCashAmount()+" | ";
		}
		log4j.log(Level.forName("NOTICE", 150), format.format(date) + " Cash balance : "+cashBalanceToPrint);
	}
	
	public static void printShareBalance(List<ShareBalance> shareBalances, Date date){
		String shareBalanceToPrint = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (ShareBalance shareBalance : shareBalances) {
			shareBalanceToPrint+= shareBalance.getCompanyCode() + ": "+ shareBalance.getAmount()+" | ";
		}
		log4j.log(Level.forName("NOTICE", 150), format.format(date) + " Shares balance : "+shareBalanceToPrint);
	}

	public static void printOffers(String commentar, List<Offer> offer, Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<offer.size(); i++){
			log4j.log(Level.forName("NOTICE", 150), format.format(date) +" "+commentar+(i+1)+": "+ offer.get(i).getCompanyCode()+ " " +offer.get(i).getAmount() + " "+offer.get(i).getPrice());
		}
	}

	public static void printCosts(BigDecimal costOfShares, BigDecimal commision, BigDecimal toPay, Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		log4j.log(Level.forName("NOTICE", 150), format.format(date) + " Cost of Share : "+costOfShares+" | Commision : "+commision+" | To pay : "+toPay);
	}
	
}
