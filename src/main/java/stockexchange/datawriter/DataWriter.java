package stockexchange.datawriter;

import java.io.IOException;

public interface DataWriter {
	void saveAllIntoDB() throws  IOException;
	void clearStocksTable();
}
