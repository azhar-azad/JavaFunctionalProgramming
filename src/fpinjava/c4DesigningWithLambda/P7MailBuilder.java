package fpinjava.c4DesigningWithLambda;

/***
 * Using Method Chaining
 * Rather than repeating the reference, it would be great to continue a conversational
 * state on a context object. We can achieve this using a simple method chaining or
 * cascade method pattern.
 */
public class P7MailBuilder {

    public P7MailBuilder from(final String address) {
        /*
        ...
         */
        return this;
    }

    public P7MailBuilder to(final String address) {
        /*
        ...
         */
        return this;
    }

    public P7MailBuilder subject(final String line) {
        /*
        ...
         */
        return this;
    }

    public P7MailBuilder body(final String message) {
        /*
        ...
         */
        return this;
    }

    public void send() {
        System.out.println("sending...");
    }

    //...

    public static void main(String[] args) {

        new P7MailBuilder()
                .from("build@xyz.com")
                .to("abc@xyz.com")
                .subject("build notification")
                .body("...it sucks less...")
                .send();
    }

    /*
    Even though this design reduces the noise, it has a few disadvantages. The new keyword
    sticks out, reducing API's fluency and readability. The design does not prevent someone
    from storing the reference from new and then chaining that from reference. And we still
    has the issue with object lifetime. Also, there are a lot of corner cases, for example,
    we have to ensure methods like from() are called exactly once.
     */
}
