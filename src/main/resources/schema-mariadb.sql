CREATE DATABASE sms;

use sms;

create table user (
    id int primary key auto_increment,
    firstName varchar(200) not null,
    lastName varchar(500),
    email varchar(255) not null,
    password varchar(255) not null,

    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT unq_email_user UNIQUE (email)
);

create table role (
    id int primary key auto_increment,
    name varchar(255) not null,
    CONSRAINT pk_role_id PRIMARY KEY (id)
);

create table students (
    id int primary key auto_increment,
    firstName varchar(200) not null,
    lastName varchar(500),
    email varchar(255) not null,
    CONSRAINT pk_student_id PRIMARY KEY (id),
    CONSTRANT unq_email_student UNIQUE (email)
);

create table users_role (
    userId int not null,
    roleId int not null,
    CONSRAINT pk_user_role_id PRIMARY KEY (userId, roleId),
    CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES user(id),
    CONSTRAINT fk_role_id FOREIGN KEY (roleId) REFERENCES role(id)
);