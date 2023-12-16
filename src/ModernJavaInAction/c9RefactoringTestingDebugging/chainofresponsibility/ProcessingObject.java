package ModernJavaInAction.c9RefactoringTestingDebugging.chainofresponsibility;

public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    /*
    The handle() method provides an outline for dealing with a piece of work.
     */
    public T handle(T input) {
        T t = handleWork(input);
        if (successor != null) {
            return successor.handle(t);
        }
        return t;
    }

    protected abstract T handleWork(T input);
}
