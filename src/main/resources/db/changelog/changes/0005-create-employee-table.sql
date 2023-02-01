create table tb_employee (
id_employee identity not null primary key,
role varchar(255)
);

--rollback
delete from tb_employee;