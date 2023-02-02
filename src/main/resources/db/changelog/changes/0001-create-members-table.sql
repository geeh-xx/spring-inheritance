create table tb_members (
id_member serial not null primary key,
name varchar(255) not null,
type varchar(255) not null,
role varchar(255),
contract_duration int,
salary numeric(5,2)
);

--rollback
delete from tb_members;