package it.elqady.hesham.service;

import it.elqady.hesham.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserParser {
    private final List<User> users = new ArrayList<User>();
    // Define the format of the input string
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserParser() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void parseUser(String line) {
        String[] tokens = line.split(" ");
        if (tokens.length != 5) {
            System.err.println("Invalid user line: " + line);
            return;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(tokens[1] + " " + tokens[2], FORMATTER);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        users.add(new User(instant, tokens[3], Integer.parseInt(tokens[4])));
    }
}
