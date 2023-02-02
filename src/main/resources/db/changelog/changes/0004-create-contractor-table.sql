create table tb_contractor (
id_contractor serial not null primary key,
contract_duration integer
);

--rollback
delete from tb_contractor;