package fpinjava.c4DesigningWithLambda;

import java.util.function.Consumer;

/***
 * Making the API Intutive and Fluent.
 */
public class P8FluentMailer {

    /**
     * We make the constructor private. This will disallow direct object creation.
     */
    private P8FluentMailer() {}

    public P8FluentMailer from(final String address) {
        /*
        ...
         */
        return this;
    }

    public P8FluentMailer to(final String address) {
        /*
        ...
         */
        return this;
    }

    public P8FluentMailer subject(final String line) {
        /*
        ...
         */
        return this;
    }

    public P8FluentMailer body(final String message) {
        /*
        ...
         */
        return this;
    }

    /**
     * We make the terminal method, send(), a static method and it
     * excepts a Consumer as a parameter.
     * Rather than creating the instance, users will now invoke send()
     * and pass a block of code. The send() method will create an instance,
     * yield it to the block, and, upon return, complete any required
     * validations and perform its final send operation.
     * The object's scope is confined within the block, and once we
     * return from send() method, the reference is gone.
     */
    public static void send(final Consumer<P8FluentMailer> block) {
        final P8FluentMailer mailer = new P8FluentMailer();
        block.accept(mailer);
        System.out.println("sending...");
    }

    //...

    public static void main(String[] args) {

        P8FluentMailer.send(mailer ->
                mailer.from("build@xyz.com")
                        .to("abc@xyz.com")
                        .subject("build notification")
                        .body("...much better..."));
    }

    /*
    We invoked the send() method and passed a lambda expression to it. Within the lambda
    expression, we received an instance of the mailer and invoked the desired chain of
    methods on it.
    The instance's scope is fairly easy to see: we get it, work with it, and return it.
    For that reason, this is also called the loan pattern.
     */
}
