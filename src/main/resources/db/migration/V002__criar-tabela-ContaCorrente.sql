create table conta_corrente (

	 id bigint not null auto_increment,
	 pessoa_id bigint not null,
	 numero varchar(6) not null,
	 agencia text not null,
	 tipo varchar(1),
	 limite decimal(10,2),
	 cartao decimal(10,2),
	 
	 primary key (id)
);

alter table conta_corrente add constraint fk_contacorrente_pessoa foreign key (pessoa_id) references pessoa (id);