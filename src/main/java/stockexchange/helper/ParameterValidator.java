package stockexchange.helper;

import org.springframework.stereotype.Component;

@Component
public class ParameterValidator {

	
	public boolean isStringCorrect(String string){
		return (!string.isEmpty());
	}
	
	public boolean isIntegerNumber(String number){
		return (!number.isEmpty() && number.matches("[0-9]+"));
	}
	
	public boolean isNumberWithComma(String number){
		return (!number.isEmpty() && number.matches("^[0-9]*\\.?[0-9]*$"));	
	}
	
	public boolean isPesel(String pesel){
		return (pesel.length()==11 && pesel.matches("[0-9]+"));	
	}
	
	
}
