package venkatfpij.c4designingwithlambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/***
 * Refactoring to Separate a Key Concern.
 * The three methods share two out of three concerns we mentioned on the AssetUtil class.
 * The iteration and the way to total are the same. The "what to total" concern is different
 * and is a good candidate to separate out of those methods.
 * This seems like a good place for the Strategy pattern.
 */
public class P2AssetUtilRefactored {

    public static void main(String[] args) {

        /*
        Let's define some assets to try out the code.
         */
        final List<Asset> assets = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000));

        /*
        Let's use the refactored version of totalAssetValues() to total the values of all the assets.
        We invoke the function, passing it the list of assets as the first argument and a succinct
        lambda expression as the second argument. As the function iterates over the assets, it invokes
        the lambda expression for each asset, asking if the asset's value should be included in the total.
        Since we want to total all the assets, we return a boolean true here.
         */
        System.out.println("Total of all assets: " + totalAssetValues(assets, asset -> true));

        /*
        Let's reuse the function to compute the total on only bonds.
         */
        System.out.println("Total of bonds: " + totalAssetValues(assets,
                asset -> asset.getType() == Asset.AssetType.BOND));

        /*
        Let's reuse the function to compute the total on only stocks.
         */
        System.out.println("Total of stocks: " + totalAssetValues(assets,
                asset -> asset.getType() == Asset.AssetType.STOCK));
    }

    /**
     * Let's refactore the three methods into one that takes a functional interface as a parameter.
     * @param assets The list of Assets
     * @param assetSelector Predicate to evaluate whether an asset should be considered.
     * @return Total of the Asset's value based on the Predicate passed to the function.
     */
    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetSelector) {
        return assets.stream()
                .filter(assetSelector)
                .mapToInt(Asset::getValue)
                .sum();
    }
}
