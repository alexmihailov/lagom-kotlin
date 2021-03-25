package com.witcher.lagom.university.api.data;

import java.time.LocalDate;

public abstract class PersonData {
    protected final String firstName;
    protected final String middleName;
    protected final String lastName;
    protected final LocalDate birthday;

    public PersonData(String firstName, String middleName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
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
