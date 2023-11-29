package fpinjava.c3StringsComparatorsFilters;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/***
 * Watching a File Change
 */
public class P7WatchFileChange {

    public static void main(String[] args) throws IOException, InterruptedException {

        final Path path = Paths.get(".");
        final WatchService watchService = path.getFileSystem().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Report any file changed within next 1 minute...");


        final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);

        if (watchKey != null) {
            watchKey.pollEvents().stream()
                    .forEach(watchEvent -> System.out.println(watchEvent.context()));
        }
    }
}
