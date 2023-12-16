package ModernJavaInAction.c9RefactoringTestingDebugging.observer;

public class KalerKontho implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet more news from London... " + tweet);
        }
    }
}
