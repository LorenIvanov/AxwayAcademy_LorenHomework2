drop database if exists loren_axway;
create database loren_axway;
use loren_axway;

create table employees(
id int not null auto_increment,
Name varchar(255),
Surname varchar(255),
Birthdate date,
primary key(id)
);

insert into employees values (null,"Loren","Ivanov",'1995-05-11');
insert into employees values (null,"Teodora","Dimitrova",'1995-02-02');
insert into employees values (null,"Petur","Petkov",'1992-06-15');
insert into employees values (null,"Kristiyan","Nikolaev",'1989-09-28');
insert into employees values (null,"Desislava","Georgieva",'1996-11-13');

select * from employees;