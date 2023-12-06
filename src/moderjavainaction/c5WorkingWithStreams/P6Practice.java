package moderjavainaction.c5WorkingWithStreams;

import utils.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/***
 * You're asked by your manager to find answers to eight queries:
 *      1. Find all transactions in the year 2011 and sort them by value (small to high).
 *      2. What are all the unique cities where the traders work?
 *      3. Find all traders from Cambridge and sort them by name.
 *      4. Return a string of all traders' names sorted alphabetically.
 *      5. Are any traders based in Milan?
 *      6. Print the values of all transactions from the traders living in Cambridge.
 *      7. What's the highest value of all the transactions?
 *      8. Find the transaction with the smallest value.
 */
public class P6Practice {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300, "1a"),
                new Transaction(raoul, 2012, 1000, "a12"),
                new Transaction(raoul, 2011, 400, "1b"),
                new Transaction(mario, 2012, 710, "b13"),
                new Transaction(mario, 2012, 700, "1c"),
                new Transaction(alan, 2012, 950, "C14")
        );

        /*
        1. Find all transactions in the year 2011 and sort them by value (small to high).
         */
        List<Transaction> transactionsOf2011Sorted = transactions.stream()
                .filter(t -> t.getYear() == 2011) // Passes a predicate to filter to select transactions in year 2011
                .sorted(Comparator.comparing(Transaction::getValue)) // Sorts them by using the value of the transaction
                .collect(Collectors.toList()); // Collects all the elements of the resulting Stream into a List
        Utils.printList(transactionsOf2011Sorted);

        /*
        2. What are all the unique cities where the traders work?
         */
        List<String> uniqueCities = transactions.stream()
                .map(t -> t.getTrader().getCity()) // Extracts the city from each trader associated with the transaction
                .distinct() // Selects only unique cities
                .collect(Collectors.toList());
        Utils.printList(uniqueCities);

        /*
        3. Find all traders from Cambridge and sort them by name.
         */
        List<Trader> tradersFromCambridgeSorted = transactions.stream()
                .map(Transaction::getTrader) // Extracts all traders from the transactions
                .filter(t -> t.getCity().equals("Cambridge")) // Selects only the traders from Cambridge
                .distinct() // Removes any duplicates
                .sorted(Comparator.comparing(Trader::getName)) // Sorts the resulting stream of traders by their names
                .collect(Collectors.toList());
        Utils.printList(tradersFromCambridgeSorted);

        /*
        4. Return a string of all traders' names sorted alphabetically.
         */
        String tradersName = transactions.stream()
                .map(Transaction::getTrader) // Extracts all the traders from the transactions
                .map(Trader::getName) // Extracts all the names of the traders as a Stream of Strings
                .distinct() // Remove duplicate names
                .sorted() // Sorts the name alphabetically
                .reduce("", (s1, s2) -> s1 + " " + s2); // Combines the names one by one to form a String
        System.out.println(tradersName);

        // or,
        tradersName = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println(tradersName);

        /*
        5. Are any traders based in Milan?
         */
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")); // Pass a predicate to anyMatch() to check if there's a trader from Milan
        System.out.println("Are there any traders based in Milan: " + (milanBased ? "yes" : "no"));

        /*
        6. Print the values of all transactions from the traders living in Cambridge.
         */
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge")) // Selects the transactions where the traders live in Cambridge
                .map(Transaction::getValue) // Extracts the values of these traders
                .forEach(System.out::println); // Prints each value

        /*
        7. What's the highest value of all the transactions?
         */
        transactions.stream()
                .map(Transaction::getValue) // Extracts the value of each transaction
                .reduce(Integer::max) // Calculates the max of the resulting stream
                .ifPresent(System.out::println);

        /*
        8. Find the transaction with the smallest value.
         */
        transactions.stream()
                .map(Transaction::getValue) // Extracts the value of each transaction
                .reduce(Integer::min) // Calculates the min of the resulting stream
                .ifPresent(System.out::println);
    }
}
