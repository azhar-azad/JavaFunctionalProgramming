package fpinjava.c4designingwithlambda;

import java.util.Arrays;
import java.util.List;

/***
 * Separating Concerns Using Lambda Expression.
 */
public class P1AssetUtil {

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
        totalAssetValues() call
         */
        System.out.println("Total of all assets: " + totalAssetValues(assets));

        /*
        totalBondValues() call
         */
        System.out.println("Total of bonds: " + totalBondValues(assets));

        /*
        totalStockValues() call
         */
        System.out.println("Total of bonds: " + totalStockValues(assets));
    }

    /**
     * Exploring Design Concerns
     * Suppose we're asked to total the values of all the assets given.
     * We'll use the convenience of lambda expressions within this function. We transformed the List of Assets into
     * a Stream, then mapped that into a Stream of values using the mapToInt() method. Finally, we reduced or
     * totaled the values in this stream to arrive at a single value using the sum() method.
     * It's good we employed lambda expressions here - we used fluent iterators and favoured immutability. But it's
     * tangled with three concerns:
     *  - how to iterate
     *  - what to total
     *  - how to total
     * This entangled logic will result in poor reuse.
     */
    public static int totalAssetValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(Asset::getValue)
                .sum();
    }

    /**
     * Getting Entangled with the Concerns.
     * Imagine we're asked to total only the bonds assets. We can levarage cope and paste and leave totalAssetValues()
     * intact, but duplicate it and modify the new version.
     * The only difference, other than their names, between totalBondValues() and totalAssetValues() is in the lambda
     * expressions we send to the mapToInt() function.
     */
    public static int totalBondValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(asset -> asset.getType() == Asset.AssetType.BOND ? asset.getValue() : 0)
                .sum();
    }

    /**
     * Getting Entangled with the Concerns.
     * As fate may have it, now we're asked to total only stocks. We can again do that morally wrong, copy and pasting.
     */
    public static int totalStockValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(asset -> asset.getType() == Asset.AssetType.STOCK ? asset.getValue() : 0)
                .sum();
    }

    /***
     * All of them functions are working but the design does not support DRY. Follow AssetUtilRefactored.
     */
}
