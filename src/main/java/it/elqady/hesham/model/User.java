package it.elqady.hesham.model;

import java.time.Instant;

public class User {
    private Instant date;
    private String name;
    private int value;

    public User() {
    }

    public User(Instant date, String name, int value) {
        this.date = date;
        this.name = name;
        this.value = value;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
