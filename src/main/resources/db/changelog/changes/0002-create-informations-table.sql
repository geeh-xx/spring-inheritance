create table tb_informations (
id_information identity not null primary key,
id_member serial,
country varchar(255) not null,
currency varchar(255) not null,
foreign key(id_member) references tb_members(id_member)
);

--rollback
delete from tb_informations;