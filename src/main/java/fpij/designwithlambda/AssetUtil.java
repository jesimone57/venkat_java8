package fpij.designwithlambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jsimone on 5/21/16.
 */
public class AssetUtil {
	public static int totalAssetValues(final List<Asset> assets) {
		return assets.stream()
				.mapToInt(Asset::getValue)
				.sum();
	}

	final static List<Asset> assets = Arrays.asList(
			new Asset(AssetType.BOND, 1000),
			new Asset(AssetType.BOND, 2000),
			new Asset(AssetType.STOCK, 3000),
			new Asset(AssetType.STOCK, 4000)
	);

	public static int totalAssetValues(final List<Asset> assets,
									   final Predicate<Asset> assetSelector) {
		return assets.stream().filter(assetSelector).mapToInt(Asset::getValue).sum();
	}

	public static Predicate<Asset> isStock = p -> p.getType() == AssetType.STOCK;
	public static Predicate<Asset> isBond = p -> p.getType() == AssetType.STOCK;

	public static void main(String[] args) {
		System.out.println("Total of all assets: " + totalAssetValues(assets));

		System.out.println("Total of all assets: " + totalAssetValues(assets, asset -> true));

		System.out.println("Total of bonds: " +
				totalAssetValues(assets, asset -> asset.getType() == AssetType.BOND));
		System.out.println("Total of stocks: " +
				totalAssetValues(assets, asset -> asset.getType() == AssetType.STOCK));

		// cleaner user predefined predicates
		System.out.println("Total of bonds: " + totalAssetValues(assets, isBond));
		System.out.println("Total of stocks: " + totalAssetValues(assets, isStock));
	}
}
