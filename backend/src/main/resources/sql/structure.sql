drop database heroes;
create database heroes;

use heroes;

create table hero(
	id int not null auto_increment,
	name varchar(100) not null,
	
	primary key (id)
);

create table image(
	id int not null,
	extension enum("jpeg", "png") not null,
	data longblob not null,
	
	primary key (id),
	foreign key (id) references hero(id)
);