package moderjavainaction.c9RefactoringTestingDebugging.factory;

import java.util.HashMap;
import java.util.Map;

public class ProductFactoryLambdaComplex {

    final static Map<String, TriFunction<Integer, Integer, String, Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name, Integer initialPrice, Integer discount, String productClass) {
        TriFunction<Integer, Integer, String, Product> p = map.get(name);
        if (p != null)
            return p.apply(initialPrice, discount, productClass);
        throw new IllegalArgumentException("No such product " + name);
    }
}
