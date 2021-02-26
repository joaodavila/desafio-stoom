create table endereco(

	id bigint not null auto_increment,
	street_name varchar(40) not null,
	number int not null,
	complement varchar(20),
	neighbourhood varchar(40) not null,
	city varchar(40) not null,
	state varchar(20) not null,
	country varchar(20) not null,
	zipcode int not null,
	latitude DECIMAL(10,8),
	longitude DECIMAL(11,8),
	
	
	primary key (id)
);