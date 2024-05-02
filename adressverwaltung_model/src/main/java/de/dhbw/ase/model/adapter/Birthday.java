package de.dhbw.ase.model.adapter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Birthday {
    public static Birthday of(LocalDate date) {
        return new Birthday(date);
    }

    private final LocalDate birthday;

    public Birthday(LocalDate date) {
        birthday = date;
    }

    public boolean isToday() {
        LocalDate today = LocalDate.now();
        return today.getDayOfMonth() == birthday.getDayOfMonth() &&
                today.getMonth() == birthday.getMonth();
    }

    public String getBirthday() {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return birthday.format(pattern);
    }

    public int getAge() {
        return Period
                .between(birthday, LocalDate.now())
                .getYears();
    }
}
