package stockexchange.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateParser {
	
	public Date stringToDate(String stringDate) throws ParseException{
		return new SimpleDateFormat("yyyyMMdd").parse(stringDate);	
	}

}
