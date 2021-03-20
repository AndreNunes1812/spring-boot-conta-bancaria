create table pessoa (
    id bigint not null auto_increment,
	tipo varchar(2) not null,
	documento varchar(14) not null,
	score integer,
	
	primary key (id)


);