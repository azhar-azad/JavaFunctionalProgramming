package venkatfpij.c2collection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class P1Iteration {

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        /*
        Habitual, but not so desirable, way to iterate and print each of the element
         */
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

        /*
        Java offers a construct that is a bit more civilized than the good old for loop.
        Under the hood this form of iteration uses the Iterator interface and calls into
        its hasNext() and next() methods.
         */
        for (String name: friends) {
            System.out.println(name);
        }

        /*
        The Iterable interface has been enhanced in JDK 8 with a special method named forEach(),
        which accepts a parameter of type Consumer.
        The forEach() method will invoke the accept() method of the given Consumer for each element
        in the collection and let it do whatever it wants with it.
         */
        friends.forEach(new Consumer<String>() {
            public void accept(final String name) {
                System.out.println(name);
            }
        });

        /*
        Let's replace the anonymous inner class with a lambda expression.
         */
        friends.forEach(name -> System.out.println(name));

        /*
        Let's use method reference instead of lambda expression.
        Java lets us simply replace the body of code with the method name of our choice.
         */
        friends.forEach(System.out::println);
    }
}
