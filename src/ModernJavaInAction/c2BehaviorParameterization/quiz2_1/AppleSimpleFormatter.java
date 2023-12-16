package ModernJavaInAction.c2BehaviorParameterization.quiz2_1;

import ModernJavaInAction.c2BehaviorParameterization.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
