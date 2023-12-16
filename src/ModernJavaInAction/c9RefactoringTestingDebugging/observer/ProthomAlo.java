package ModernJavaInAction.c9RefactoringTestingDebugging.observer;

public class ProthomAlo implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in Prothom Alo! " + tweet);
        }
    }
}
