package moderjavainaction.c9RefactoringTestingDebugging;

import moderjavainaction.c9RefactoringTestingDebugging.observer.Feed;
import moderjavainaction.c9RefactoringTestingDebugging.observer.Jugantor;
import moderjavainaction.c9RefactoringTestingDebugging.observer.KalerKontho;
import moderjavainaction.c9RefactoringTestingDebugging.observer.ProthomAlo;

/***
 * Observer Pattern
 * The observer design pattern is a common solution when an object (called the subject) needs to automatically notify
 * a list of other objects (called observers) when some event happens (such as a state change).
 */
public class P3ObserverPattern {

    public static void main(String[] args) {

        /*
        You'll design and implement a customized notification system for an application such as Twitter. The concept is
        simple: several newspaper agencies (Prothom Alo, Kaler Kontho, and Jugantor) are subscribed to a feed of news
        tweets and may want to receive a notification if a tweet contains a particular keyword.
         */
        /*
        First, you need an Observer interface that groups the observers. It has one method, called notify, that will
        be called by the subject (Feed) when a new tweet is available.
        See Observer interface.
         */
        /*
        Now, you can declare different observers (here, the three newspaper) that produce a different action for each
        different keyword contained in a tweet.
        See ProthomAlo, KalerKontho, and Jugantor classes.
         */
        /*
        You're still missing the crucial part: the subject. j
        See Subject interface.
         */
        /*
        The subject can register a new observer using the registerObserver() method and notify his observers of a tweet
        with the notifyObservers() method. Lets implement the Subject.
        See the Feed class.
         */
        /*
        The implementation is straightforward: the feed keeps an internal list of observers that it can notify when a
        tweet arrives. Let's use
         */
        Feed f = new Feed();
        f.registerObserver(new ProthomAlo());
        f.registerObserver(new KalerKontho());
        f.registerObserver(new Jugantor());
        f.notifyObservers("The queen said her favourite book is a recipe book");
        // Yet more news from London... The queen said her favourite book is a recipe book

        /*
        Using Lambda Expressions
        Notice that the various classes that implement the Observer interface all provide implementation for a single
        method: notify(). They're wrapping a piece of behavior to execute when a tweet arrives. Lambda expressions are
        designed specifically to remove that boilerplate. Instead of instantiating three observer objects explicitly,
        you can pass a lambda expression directly to represent the behavior to execute.
         */
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in Prothom Alo! " + tweet);
            }
        });
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London... " + tweet);
            }
        });
        /*
        Should you use lambda expressions all the time? The answer is no. In the example we described, lambda
        expressions work great because the behavior to execute is simple, so they're helpful for removing boilerplate
        code. But the observers may be more complex; they could have state, define several methods, and the like. In
        those situations, you should stick with classes.
         */
    }
}
