package stockexchange.datawriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class DataCSVReader {
	
	private CSVReader csvReader;
	private FileReader fileReader;
	@Value(value = "#{simulationProperties['inputDataFilePath']}")
	private String inputDataFilePath;

	public void openFileReaders() throws FileNotFoundException{
		System.out.println(inputDataFilePath);
		fileReader = new FileReader(inputDataFilePath);
		csvReader = new CSVReader(fileReader);
	}
	
	public String[] readLine() throws IOException {
		return csvReader.readNext();	
	}
	
	public void closeReaders() throws IOException{
		fileReader.close();
		csvReader.close();
	}

}
