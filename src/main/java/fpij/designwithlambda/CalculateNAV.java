package fpij.designwithlambda;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by jsimone on 5/21/16.
 */
public class CalculateNAV {
	private Function<String, BigDecimal> priceFinder;

	public CalculateNAV(final Function<String, BigDecimal> priceFinderFunction) {
		priceFinder = priceFinderFunction;
	}


	public BigDecimal computeStockWorth(final String ticker, final int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}

	public static void main(String[] args) {
		final CalculateNAV calculateNav = new CalculateNAV(YahooFinance::getPrice);
		System.out.println(String.format("100 shares of Google worth: $%.2f", calculateNav.computeStockWorth("GOOG", 100)));
	}
	//... other methods that use the priceFinder ...
}
