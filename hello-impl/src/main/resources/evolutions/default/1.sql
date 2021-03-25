# --- !Ups

create table students(
    id varchar(36) primary key,
    first_name varchar(100) not null,
    middle_name varchar(100) not null,
    last_name varchar(100) not null,
    student_group varchar(50) not null,
    birthday date not null
);

# --- !Downs

drop table students;
