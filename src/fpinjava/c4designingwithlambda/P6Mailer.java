package fpinjava.c4designingwithlambda;

/***
 * Creating Fluent Interfaces Using Lambda Expressions.
 * This version has two issues,
 *  - Repeated use of mailer reference.
 *  - Unclear object lifetime.
 */
public class P6Mailer {

    public void from(final String address) {}
    public void to(final String address) {}
    public void subject(final String line) {}
    public void body(final String message) {}
    public void send() {
        System.out.println("sending...");
    }

    //...

    public static void main(String[] args) {

        P6Mailer mailer = new P6Mailer();
        mailer.from("build@xyz.com");
        mailer.to("abc@xyz.com");
        mailer.subject("build notification");
        mailer.body("...your code sucks...");
        mailer.send();
    }
}
