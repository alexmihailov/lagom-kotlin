package com.witcher.lagom.university.api.data;

import java.time.LocalDate;

public class StudentData extends PersonData {
    private final String group;

    public StudentData(String firstName, String middleName, String lastName, LocalDate birthday, String group) {
        super(firstName, middleName, lastName, birthday);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
