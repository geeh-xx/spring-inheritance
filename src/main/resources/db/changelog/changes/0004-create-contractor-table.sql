create table tb_contractor (
id_contractor identity not null primary key,
contract_duration integer
);

--rollback
delete from tb_contractor;