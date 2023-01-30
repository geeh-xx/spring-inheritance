create table tb_informations (
id_information serial not null primary key,
country varchar(255) not null,
currency varchar(255) not null
);

--rollback
delete from tb_informations;