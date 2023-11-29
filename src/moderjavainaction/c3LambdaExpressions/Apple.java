package moderjavainaction.c3LambdaExpressions;

import moderjavainaction.c2BehaviorParameterization.Color;

public class Apple {

    private Integer weight;
    private String country;
    private Color color;

    public Apple() {
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(Color color, Integer integer) {
    }

    public Integer getWeight() {
        return this.weight;
    }

    public String getCountry() {
        return this.country;
    }

    public Color getColor() {
        return this.color;
    }
}
