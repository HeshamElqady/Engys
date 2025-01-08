package it.elqady.hesham.service;

import it.elqady.hesham.model.User;
import it.elqady.hesham.model.UserStats;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService {
    private final static String FILE_NAME = "producer.log";
    private static final UserParser USER_PARSER = new UserParser();

    public FileService() {
    }

    public void printFileContent() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                USER_PARSER.parseUser(line);
            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMinnie() {
        USER_PARSER.getUsers().stream()
                .filter(user -> user.getName().equals("Minnie"))
                .sorted(Comparator.comparing(User::getValue))
                .forEach(System.out::println);
        System.out.println("\n");

    }

    public void printStats() {
        Map<String, UserStats> userStatsMap = getUserStatsMap(USER_PARSER.getUsers());
        System.out.printf("%-10s %-10s %-10s %-10s%n", "User", "Min", "Max", "Average");
        userStatsMap.forEach((name, stats) -> {
            System.out.printf("%-10s %-10d %-10d %-10.3f%n", name, stats.getMin(), stats.getMax(), stats.getAverage());
        });
        System.out.println("\n");
    }

    public static Map<String, UserStats> getUserStatsMap(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(
                        User::getName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new UserStats(
                                        list.stream().mapToInt(User::getValue).min().orElse(0),
                                        list.stream().mapToInt(User::getValue).max().orElse(0),
                                        list.stream().mapToInt(User::getValue).average().orElse(0.0)
                                )
                        )
                ));
    }

}
