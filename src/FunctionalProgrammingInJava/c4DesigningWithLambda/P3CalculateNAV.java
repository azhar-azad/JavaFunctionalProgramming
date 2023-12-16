package FunctionalProgrammingInJava.c4DesigningWithLambda;

import java.math.BigDecimal;
import java.util.function.Function;

/***
 * Delegating Using Lambda Expression
 * Creating a Delegate - Rather than delegating part of the responsibility to another class, we can
 * delegate it to lambda expression and method references. This will further reduce class proliferation.
 *
 * This class will perform financial calculations with data from a web service.
 */
public class P3CalculateNAV {

    /**
     * We want to send a ticker symbol and receive a price, hopefully obtained from a web service.
     */
    private Function<String, BigDecimal> priceFinder;

    /**
     * Let's initialize the field through a constructor injection rather than coupling to an implementation
     * directly within the class. In effect we're using dependency injection and the dependency inversion
     * principle.
     */
    public P3CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
        priceFinder = aPriceFinder;
    }

    /**
     * In this method, we request the price of a ticker from a (yet to be defined) priceFinder and
     * determine the net worth based on the number of shares.
     */
    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }

    // ... other methods that use the priceFinder ...

    public static void main(String[] args) {
        final P3CalculateNAV calculateNAV = new P3CalculateNAV(YahooFinance::getPrice);

        System.out.printf("100 shares of Google worth: $%.2f%n", calculateNAV.computeStockWorth("GOOG", 100));
    }
}
