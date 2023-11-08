package moderjavainaction.c2behaviorparameterization.quiz2_1;

import moderjavainaction.c2behaviorparameterization.Apple;

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
