package ModernJavaInAction.c9RefactoringTestingDebugging.chainofresponsibility;

public class SpellCheckProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
