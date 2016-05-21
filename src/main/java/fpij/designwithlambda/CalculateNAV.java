package fpij.designwithlambda;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by jsimone on 5/21/16.
 */
public class CalculateNAV {
	private Function<String, BigDecimal> priceFinder;

	public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
		priceFinder = aPriceFinder;
	}

	public BigDecimal computeStockWorth(final String ticker, final int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}
	//... other methods that use the priceFinder ...
}
