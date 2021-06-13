create database wcis;
use wcis;

create table studentinfo
(
id int not null primary key auto_increment,
sname varchar(55) not null,
age int not null,
course varchar(55) not null,
mobile varchar(15) not null,
city varchar(25) not null
);