create database if not exists baseball;

use baseball;

drop table if exists baseball;

create table baseball (
	id int(10) not null auto_increment,
	name varchar(40) not null,
	location varchar(20) not null,
	primary key(id)
);