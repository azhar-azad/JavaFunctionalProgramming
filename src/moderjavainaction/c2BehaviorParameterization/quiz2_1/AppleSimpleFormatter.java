package moderjavainaction.c2BehaviorParameterization.quiz2_1;

import moderjavainaction.c2BehaviorParameterization.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
