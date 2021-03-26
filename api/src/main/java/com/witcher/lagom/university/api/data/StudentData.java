package com.witcher.lagom.university.api.data;

import java.time.LocalDate;

public class StudentData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final LocalDate birthday;
    private final String group;

    public StudentData(String firstName, String middleName, String lastName, LocalDate birthday, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
