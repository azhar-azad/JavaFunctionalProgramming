package venkatfpij.c4designingwithlambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class YahooFinance {

    /***
     *
     * @param ticker
     * @return
     */
    public static BigDecimal getPrice(final String ticker) {
//        try {
//            final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
//
//            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            final String data = reader.lines().skip(1).findFirst().get();
//            final String[] dataItems = data.split(",");
//            return new BigDecimal(dataItems[dataItems.length - 1]);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // or just return something random
        Random random = new Random();
        return BigDecimal.valueOf(random.nextFloat());
    }
}
