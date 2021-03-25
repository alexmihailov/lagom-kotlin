package com.witcher.lagom.university.impl.persistence.students;

import com.google.common.base.Objects;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class StudentEntity extends PersonEntity {

    @Id
    private int id;

    @Column(name = "student_group")
    private String group;

    public StudentEntity() {
    }

    public StudentEntity(String firstName, String middleName, String lastName, LocalDate birthday, String group) {
        super(firstName, middleName, lastName, birthday);
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return id == that.id && Objects.equal(group, that.group)
                && Objects.equal(firstName, that.firstName)
                && Objects.equal(middleName, that.middleName)
                && Objects.equal(lastName, that.lastName)
                && Objects.equal(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, middleName,
                lastName, birthday, group);
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
