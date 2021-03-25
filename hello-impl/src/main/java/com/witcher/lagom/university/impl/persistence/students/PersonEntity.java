package com.witcher.lagom.university.impl.persistence.students;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersonEntity {

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "last_name")
    protected String lastName;

    @Column
    protected LocalDate birthday;

    public PersonEntity() {
    }

    public PersonEntity(String firstName, String middleName, String lastName, LocalDate birthday) {
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
