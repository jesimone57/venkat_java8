package fpij.designwithlambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by jsimone on 5/21/16.
 */
public class YahooFinance {
	public static BigDecimal getPrice(final String ticker) {
		try {
			final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			//reader.lines().forEach(System.out::println);
			/*
			returns :
				Date,Open,High,Low,Close,Volume,Adj Close
				2016-05-20,701.619995,714.580017,700.52002,709.73999,1816000,709.73999
				2016-05-19,702.359985,706.00,696.799988,700.320007,1656300,700.320007
				2016-05-18,703.669983,711.599976,700.630005,706.630005,1763400,706.630005
				2016-05-17,715.98999,721.52002,704.109985,706.22998,1999500,706.22998
				...
			 */

			final String data = reader.lines().skip(1).findFirst().get();
			final String[] dataItems = data.split(",");
			return new BigDecimal(dataItems[dataItems.length - 1]); // get the close (last item in list)
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}

