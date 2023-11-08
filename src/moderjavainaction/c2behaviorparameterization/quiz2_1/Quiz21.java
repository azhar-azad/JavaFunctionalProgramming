package moderjavainaction.c2behaviorparameterization.quiz2_1;

import moderjavainaction.c2behaviorparameterization.Apple;
import moderjavainaction.c2behaviorparameterization.Color;

import java.util.ArrayList;
import java.util.List;

/***
 * Write a prettyPrintApple method that takes a List of Apples and that can be
 * parameterized with multiple ways to generate a String output from an apple (a bit
 * like multiple customized toString methods). For example, you could tell your
 * prettyPrintApple method to print only the weight of each apple. In addition, you
 * could tell your prettyPrintApple method to print each apple individually and mention whether it’s heavy or light.
 * The solution is similar to the filtering examples we’ve
 * explored so far.
 *
 * To help you get started, we provide a rough skeleton of the prettyPrintApple method:
 *
 * public static void prettyPrintApple(List<Apple> inventory, ???) {
 *     for(Apple apple: inventory) {
 *         String output = ???.???(apple);
 *         System.out.println(output);
 *     }
 * }
 */
public class Quiz21 {

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple: inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.GREEN, 200));
        inventory.add(new Apple(Color.RED, 250));
        inventory.add(new Apple(Color.GREEN, 100));
        inventory.add(new Apple(Color.RED, 120));
        inventory.add(new Apple(Color.GREEN, 300));

        prettyPrintApple(inventory, new AppleSimpleFormatter());
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}
