package car.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FileLogger implements RepositoryObserver {
    private static final String LOG_FILE = "data/log.txt";

    @Override
    public void update(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.printf("[%s] %s%n", LocalDateTime.now(), message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}