package moderjavainaction.c2behaviorparameterization.quiz2_1;

import moderjavainaction.c2behaviorparameterization.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
