package com.witcher.lagom.university.impl.persistence.students;

import com.google.common.base.Objects;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private LocalDate birthday;

    @Column(name = "student_group")
    private String group;

    public StudentEntity() {
    }

    public StudentEntity(String firstName, String middleName, String lastName, LocalDate birthday, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.group = group;
    }

    public int getId() {
        return id;
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

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id && Objects.equal(firstName, that.firstName)
                && Objects.equal(middleName, that.middleName)
                && Objects.equal(lastName, that.lastName)
                && Objects.equal(birthday, that.birthday)
                && Objects.equal(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, middleName, lastName, birthday, group);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", id=" + id +
                ", group='" + group + '\'' +
                '}';
    }
}
