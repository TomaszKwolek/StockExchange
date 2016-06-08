package stockexchange.datawriter;

import org.springframework.stereotype.Component;

@Component
public class Validator {
	
	private final int compCodeIndex=0;
	private final int dateStringIndex=1;
	private final int priceStringIndex=2;
	
	public boolean isLineCorrect(String[] line){
		return (line.length==3 && isCompanyCodeCorrect(line[compCodeIndex])
				&& isDateStringCorrect(line[dateStringIndex]) && isPriceCorrect(line[priceStringIndex]));
	}
	
	public boolean isCompanyCodeCorrect(String companyCode){
		return companyCode.matches("[a-zA-Z]+");	
	}
	
	public boolean isDateStringCorrect(String dateString){
		return !dateString.isEmpty() && dateString.matches("[0-9]+");
	}
	
	public boolean isPriceCorrect(String price){
		return !price.isEmpty() && price.matches("^[0-9]*\\.?[0-9]*$");	
	}

}
